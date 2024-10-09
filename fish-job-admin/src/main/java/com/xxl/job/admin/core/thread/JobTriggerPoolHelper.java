package com.xxl.job.admin.core.thread;

import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.admin.core.trigger.TriggerTypeEnum;
import com.xxl.job.admin.core.trigger.XxlJobTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * job trigger thread pool helper
 *
 * @author xuxueli
 * @since 2018-07-03 21:08:07
 */
public class JobTriggerPoolHelper {

	private static final Logger logger = LoggerFactory.getLogger(JobTriggerPoolHelper.class);

	// ---------------------- trigger pool ----------------------

	// fast/slow thread pool
	private ThreadPoolExecutor fastTriggerPool = null;

	private ThreadPoolExecutor slowTriggerPool = null;

	public void start() {
		fastTriggerPool = new ThreadPoolExecutor(10, XxlJobAdminConfig.getAdminConfig().getTriggerPoolFastMax(), 60L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "xxl-job, admin JobTriggerPoolHelper-fastTriggerPool-" + r.hashCode());
					}
				});

		slowTriggerPool = new ThreadPoolExecutor(10, XxlJobAdminConfig.getAdminConfig().getTriggerPoolSlowMax(), 60L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<>(2000), new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "xxl-job, admin JobTriggerPoolHelper-slowTriggerPool-" + r.hashCode());
					}
				});
	}

	public void stop() {
		// triggerPool.shutdown();
		fastTriggerPool.shutdownNow();
		slowTriggerPool.shutdownNow();
		logger.info(">>>>>>>>> xxl-job trigger thread pool shutdown success.");
	}

	// job timeout count
	private volatile long minTim = System.currentTimeMillis() / 60000; // ms > min

	private final ConcurrentMap<Integer, AtomicInteger> jobTimeoutCountMap = new ConcurrentHashMap<>();

	/**
	 * add trigger
	 */
	public void addTrigger(final int jobId, final TriggerTypeEnum triggerType, final int failRetryCount,
			final String executorShardingParam, final String executorParam, final String addressList) {

		// choose thread pool，队列长度是 1000
		ThreadPoolExecutor triggerPool_ = fastTriggerPool;
		AtomicInteger jobTimeoutCount = jobTimeoutCountMap.get(jobId);
		// 1 min 执行超过 10 次，就放到慢线程池，队列长度是 2000
		if (jobTimeoutCount != null && jobTimeoutCount.get() > 10) {
			triggerPool_ = slowTriggerPool;
		}

		// trigger
		triggerPool_.execute(() -> {

			long start = System.currentTimeMillis();

			try {
				// do trigger
				XxlJobTrigger.trigger(jobId, triggerType, failRetryCount, executorShardingParam, executorParam,
						addressList);
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			finally {

				// check timeout-count-map，只统计一分钟内的
				long minTim_now = System.currentTimeMillis() / 60000;
				if (minTim != minTim_now) {
					minTim = minTim_now;
					jobTimeoutCountMap.clear();
				}

				// incr timeout-count-map
				long cost = System.currentTimeMillis() - start;
				// ob-timeout threshold 500ms
				if (cost > 500) {
					// putIfAbsent() 没有值的话才会 put 进去，有值的话直接返回旧的
					AtomicInteger timeoutCount = jobTimeoutCountMap.putIfAbsent(jobId, new AtomicInteger(1));
					if (timeoutCount != null) {
						timeoutCount.incrementAndGet();
					}
				}

			}

		});
	}

	// ---------------------- helper ----------------------

	private static final JobTriggerPoolHelper helper = new JobTriggerPoolHelper();

	public static void toStart() {
		helper.start();
	}

	public static void toStop() {
		helper.stop();
	}

	/**
	 * @param jobId
	 * @param triggerType
	 * @param failRetryCount >=0: use this param <0: use param from job info config
	 * @param executorShardingParam
	 * @param executorParam null: use job param not null: cover job param
	 */
	public static void trigger(int jobId, TriggerTypeEnum triggerType, int failRetryCount, String executorShardingParam,
			String executorParam, String addressList) {
		helper.addTrigger(jobId, triggerType, failRetryCount, executorShardingParam, executorParam, addressList);
	}

}
