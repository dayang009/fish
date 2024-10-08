package com.xxl.job.admin.core.route.strategy;

import com.xxl.job.admin.core.route.ExecutorRouter;
import com.fish.common.core.util.ReturnT;
import com.xxl.job.core.biz.model.TriggerParam;

import java.util.List;
import java.util.Random;

/**
 * @author xuxueli
 * @since 2017/3/10
 */
public class ExecutorRouteRandom extends ExecutorRouter {

	private static final Random localRandom = new Random();

	@Override
	public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
		String address = addressList.get(localRandom.nextInt(addressList.size()));
		return ReturnT.instance(address);
	}

}
