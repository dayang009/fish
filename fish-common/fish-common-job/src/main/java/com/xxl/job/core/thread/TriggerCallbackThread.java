package com.xxl.job.core.thread;

import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.fish.common.core.util.ReturnT;
import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.enums.RegistryConfig;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.log.XxlJobFileAppender;
import com.xxl.job.core.util.FileUtil;
import com.xxl.job.core.util.JdkSerializeTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xuxueli
 * @since 2016/7/22
 */
@SuppressWarnings("unchecked")
public class TriggerCallbackThread {

	private static final Logger logger = LoggerFactory.getLogger(TriggerCallbackThread.class);

	private static final TriggerCallbackThread instance = new TriggerCallbackThread();

	private static final String failCallbackFilePath = XxlJobFileAppender.getLogPath()
		.concat(File.separator)
		.concat("callbacklog")
		.concat(File.separator);

	private static final String failCallbackFileName = failCallbackFilePath.concat("xxl-job-callback-{x}")
		.concat(".log");

	/**
	 * job results callback queue
	 */
	private final LinkedBlockingQueue<HandleCallbackParam> callBackQueue = new LinkedBlockingQueue<>();

	/**
	 * callback thread
	 */
	private Thread triggerCallbackThread;

	private Thread triggerRetryCallbackThread;

	private volatile boolean toStop = false;

	public static TriggerCallbackThread getInstance() {
		return instance;
	}

	public static void pushCallBack(HandleCallbackParam callback) {
		getInstance().callBackQueue.add(callback);
		logger.debug(">>>>>>>>>>> xxl-job, push callback request, logId:{}", callback.getLogId());
	}

	public void start() {

		// valid
		if (XxlJobExecutor.getAdminBizList() == null) {
			logger.warn(">>>>>>>>>>> xxl-job, executor callback config fail, adminAddresses is null.");
			return;
		}

		// callback
		triggerCallbackThread = new Thread(() -> {

			// normal callback
			while (!toStop) {
				try {
					HandleCallbackParam callback = getInstance().callBackQueue.take();
					// callback list param
					List<HandleCallbackParam> callbackParamList = new ArrayList<>();
					int drainToNum = getInstance().callBackQueue.drainTo(callbackParamList);
					callbackParamList.add(callback);

					// callback, will retry if error
					doCallback(callbackParamList);
				}
				catch (Exception e) {
					if (!toStop) {
						logger.error(e.getMessage(), e);
					}
				}
			}

			// last callback
			try {
				List<HandleCallbackParam> callbackParamList = new ArrayList<>();
				int drainToNum = getInstance().callBackQueue.drainTo(callbackParamList);
				if (!callbackParamList.isEmpty()) {
					doCallback(callbackParamList);
				}
			}
			catch (Exception e) {
				if (!toStop) {
					logger.error(e.getMessage(), e);
				}
			}
			logger.info(">>>>>>>>>>> xxl-job, executor callback thread destroy.");

		});
		triggerCallbackThread.setDaemon(true);
		triggerCallbackThread.setName("xxl-job, executor TriggerCallbackThread");
		triggerCallbackThread.start();

		// retry
		triggerRetryCallbackThread = new Thread(() -> {
			while (!toStop) {
				try {
					retryFailCallbackFile();
				}
				catch (Exception e) {
					if (!toStop) {
						logger.error(e.getMessage(), e);
					}

				}
				try {
					TimeUnit.SECONDS.sleep(RegistryConfig.BEAT_TIMEOUT);
				}
				catch (InterruptedException e) {
					if (!toStop) {
						logger.error(e.getMessage(), e);
					}
				}
			}
			logger.info(">>>>>>>>>>> xxl-job, executor retry callback thread destroy.");
		});
		triggerRetryCallbackThread.setDaemon(true);
		triggerRetryCallbackThread.start();

	}

	public void toStop() {
		toStop = true;
		// stop callback, interrupt and wait
		// support empty admin address
		if (triggerCallbackThread != null) {
			triggerCallbackThread.interrupt();
			try {
				triggerCallbackThread.join();
			}
			catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}

		// stop retry, interrupt and wait
		if (triggerRetryCallbackThread != null) {
			triggerRetryCallbackThread.interrupt();
			try {
				triggerRetryCallbackThread.join();
			}
			catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	// ---------------------- fail-callback file ----------------------

	/**
	 * do callback, will retry if error
	 * @param callbackParamList
	 */
	private void doCallback(List<HandleCallbackParam> callbackParamList) {
		boolean callbackRet = false;
		// callback, will retry if error
		for (AdminBiz adminBiz : XxlJobExecutor.getAdminBizList()) {
			try {
				ReturnT<String> callbackResult = adminBiz.callback(callbackParamList);
				if (callbackResult != null && ReturnT.SUCCESS_CODE == callbackResult.getCode()) {
					callbackLog(callbackParamList, "<br>----------- xxl-job job callback finish.");
					callbackRet = true;
					break;
				}
				else {
					callbackLog(callbackParamList,
							"<br>----------- xxl-job job callback fail, callbackResult:" + callbackResult);
				}
			}
			catch (Exception e) {
				callbackLog(callbackParamList,
						"<br>----------- xxl-job job callback error, errorMsg:" + e.getMessage());
			}
		}
		if (!callbackRet) {
			appendFailCallbackFile(callbackParamList);
		}
	}

	/**
	 * callback log
	 */
	private void callbackLog(List<HandleCallbackParam> callbackParamList, String logContent) {
		for (HandleCallbackParam callbackParam : callbackParamList) {
			String logFileName = XxlJobFileAppender.makeLogFileName(new Date(callbackParam.getLogDateTim()),
					callbackParam.getLogId());
			XxlJobContext.setXxlJobContext(new XxlJobContext(-1, null, logFileName, -1, -1));
			XxlJobHelper.log(logContent);
		}
	}

	private void appendFailCallbackFile(List<HandleCallbackParam> callbackParamList) {
		// valid
		if (callbackParamList == null || callbackParamList.isEmpty()) {
			return;
		}

		// append file
		byte[] callbackParamList_bytes = JdkSerializeTool.serialize(callbackParamList);

		File callbackLogFile = new File(
				failCallbackFileName.replace("{x}", String.valueOf(System.currentTimeMillis())));
		if (callbackLogFile.exists()) {
			for (int i = 0; i < 100; i++) {
				callbackLogFile = new File(failCallbackFileName.replace("{x}",
						String.valueOf(System.currentTimeMillis()).concat("-").concat(String.valueOf(i))));
				if (!callbackLogFile.exists()) {
					break;
				}
			}
		}
		FileUtil.writeFileContent(callbackLogFile, callbackParamList_bytes);
	}

	private void retryFailCallbackFile() {

		// valid
		File callbackLogPath = new File(failCallbackFilePath);
		if (!callbackLogPath.exists()) {
			return;
		}
		if (callbackLogPath.isFile()) {
			callbackLogPath.delete();
		}
		if (!(callbackLogPath.isDirectory() && callbackLogPath.list() != null && callbackLogPath.list().length > 0)) {
			return;
		}

		// load and clear file, retry
		for (File callbaclLogFile : callbackLogPath.listFiles()) {
			byte[] callbackParamList_bytes = FileUtil.readFileContent(callbaclLogFile);

			// avoid empty file
			if (callbackParamList_bytes == null || callbackParamList_bytes.length < 1) {
				callbaclLogFile.delete();
				continue;
			}

			List<HandleCallbackParam> callbackParamList = (List<HandleCallbackParam>) JdkSerializeTool
				.deserialize(callbackParamList_bytes, List.class);

			callbaclLogFile.delete();
			doCallback(callbackParamList);
		}

	}

}
