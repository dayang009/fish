package com.fish.user.service;

import com.fish.user.bridge.abst.RegisterLoginComponent;
import com.fish.user.bridge.abst.factory.RegisterLoginComponentFactory;
import com.fish.user.bridge.function.RegisterLoginByGitee;
import com.fish.user.bridge.function.RegisterLoginFuncInterface;
import com.fish.user.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 桥接模式代码的调用位置
 */
@Service
public class UserBridgeService {

	public String login(String account, String password) {
		RegisterLoginFuncInterface component = new RegisterLoginComponent(new RegisterLoginByGitee());
		return component.login(account, password);
	}

	public String register(UserInfo userInfo) {
		RegisterLoginFuncInterface component = RegisterLoginComponentFactory.getComponent("Default");
		return component.register(userInfo);
	}

	public String login3rd(HttpServletRequest request, String type) {
		RegisterLoginFuncInterface component = RegisterLoginComponentFactory.getComponent(type);
		return component.login3rd(request);
	}

}
