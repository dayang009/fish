package com.fish.user.bridge.function;

import com.fish.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class RegisterLoginByWeChat extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	@Override
	public String login(String account, String password) {
		return super.login(account, password);
	}

	@Override
	public String register(UserInfo userInfo) {
		return super.register(userInfo);
	}

	@Override
	public boolean checkUserExists(String userName) {
		return super.checkUserExists(userName);
	}

	@Override
	public String login3rd(HttpServletRequest request) {
		return super.login3rd(request);
	}

}
