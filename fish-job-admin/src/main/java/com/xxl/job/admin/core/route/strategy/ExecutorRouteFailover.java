package com.xxl.job.admin.core.route.strategy;

import com.fish.common.core.util.ReturnT;
import com.xxl.job.admin.core.route.ExecutorRouter;
import com.xxl.job.admin.core.scheduler.XxlJobScheduler;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.biz.model.TriggerParam;

import java.util.List;

/**
 * @author xuxueli
 * @since 17/3/10.
 */
public class ExecutorRouteFailover extends ExecutorRouter {

	@Override
	public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {

		StringBuilder beatResultSB = new StringBuilder();
		for (String address : addressList) {
			// beat
			ReturnT<String> beatResult = null;
			try {
				ExecutorBiz executorBiz = XxlJobScheduler.getExecutorBiz(address);
				beatResult = executorBiz.beat();
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				beatResult = new ReturnT<>(ReturnT.FAIL_CODE, "" + e);
			}
			beatResultSB.append((!beatResultSB.isEmpty()) ? "<br><br>" : "")
				.append(I18nUtil.getString("jobconf_beat"))
				.append("：")
				.append("<br>address：")
				.append(address)
				.append("<br>code：")
				.append(beatResult.getCode())
				.append("<br>msg：")
				.append(beatResult.getMsg());

			// beat success
			if (beatResult.getCode() == ReturnT.SUCCESS_CODE) {

				beatResult.setMsg(beatResultSB.toString());
				beatResult.setContent(address);
				return beatResult;
			}
		}
		return ReturnT.instance(ReturnT.FAIL_CODE, beatResultSB.toString());

	}

}
