package com.xxl.job.admin.core.route.strategy;

import com.xxl.job.admin.core.route.ExecutorRouter;
import com.fish.common.core.util.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;

import java.util.List;

/**
 * @author xuxueli
 * @since 2017/3/10
 */
public class ExecutorRouteFirst extends ExecutorRouter {

	@Override
	public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
		return ReturnT.instance(addressList.get(0));
	}

}
