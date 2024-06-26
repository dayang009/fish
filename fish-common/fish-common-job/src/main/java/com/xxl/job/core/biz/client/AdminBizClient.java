package com.xxl.job.core.biz.client;

import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.fish.common.core.util.ReturnT;
import com.xxl.job.core.util.XxlJobRemotingUtil;

import java.util.List;

/**
 * admin api test
 *
 * @author xuxueli
 * @since 2017-07-28 22:14:52
 */
@SuppressWarnings("unchecked")
public class AdminBizClient implements AdminBiz {

	private final int timeout = 3;

	private String addressUrl;

	private String accessToken;

	public AdminBizClient() {
	}

	public AdminBizClient(String addressUrl, String accessToken) {
		this.addressUrl = addressUrl;
		this.accessToken = accessToken;

		// valid
		if (!this.addressUrl.endsWith("/")) {
			this.addressUrl = this.addressUrl + "/";
		}
	}

	@Override
	public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
		return XxlJobRemotingUtil.postBody(addressUrl + "api/callback", accessToken, timeout, callbackParamList,
				String.class);
	}

	@Override
	public ReturnT<String> registry(RegistryParam registryParam) {
		return XxlJobRemotingUtil.postBody(addressUrl + "api/registry", accessToken, timeout, registryParam,
				String.class);
	}

	@Override
	public ReturnT<String> registryRemove(RegistryParam registryParam) {
		return XxlJobRemotingUtil.postBody(addressUrl + "api/registryRemove", accessToken, timeout, registryParam,
				String.class);
	}

}
