package com.fish.user.adapter;

import com.fish.user.config.LoginGiteeConfig;
import com.fish.user.service.UserService;
import com.fish.user.util.HttpClientUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget {

	@Resource
	private LoginGiteeConfig loginGiteeConfig;

	@Resource
	private Gson gson;

	@Override
	public String loginByGitee(String code, String state) {
		if (!loginGiteeConfig.getState().equals(state)) {
			throw new UnsupportedOperationException("Invalid state!");
		}

		String tokenUrl = loginGiteeConfig.getTokenUrl().concat(code);

		String execute = HttpClientUtils.execute(loginGiteeConfig.getTokenUrl(), HttpMethod.POST);

		return execute;
	}

	@Override
	public String loginByGithub(String... params) {
		return "";
	}

	@Override
	public String loginByQQ(String... params) {
		return "";
	}

	@Override
	public String loginByWechat(String... params) {
		return "";
	}

}
