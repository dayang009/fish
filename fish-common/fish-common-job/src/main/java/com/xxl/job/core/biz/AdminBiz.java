package com.xxl.job.core.biz;

import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.fish.common.core.util.ReturnT;

import java.util.List;

/**
 * @author xuxueli
 * @since 2017-07-27 21:52:49
 */
public interface AdminBiz {

	// ---------------------- callback ----------------------

	/**
	 * callback
	 * @param callbackParamList
	 * @return
	 */
	ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);

	// ---------------------- registry ----------------------

	/**
	 * registry
	 * @param registryParam
	 * @return
	 */
	ReturnT<String> registry(RegistryParam registryParam);

	/**
	 * registry remove
	 * @param registryParam
	 * @return
	 */
	ReturnT<String> registryRemove(RegistryParam registryParam);

	// ---------------------- biz (custome) ----------------------
	// group、job ... manage

}
