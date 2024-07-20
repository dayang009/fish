package com.fish.user.bridge.abst;

import com.fish.user.bridge.function.RegisterLoginFuncInterface;
import com.fish.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class RegisterLoginComponent extends AbstractRegisterLoginComponent implements RegisterLoginFuncInterface {

	/**
	 * 通过有参构造函数，初始化接口属性
	 * @param funcInterface 桥梁的具体类型
	 */
	public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
		super(funcInterface);
	}

	@Override
	public String login(String username, String password) {
		return funcInterface.login(username, password);
	}

	@Override
	public String register(UserInfo userInfo) {
		return funcInterface.register(userInfo);
	}

	@Override
	public boolean checkUserExists(String userName) {
		return funcInterface.checkUserExists(userName);
	}

	@Override
	public String login3rd(HttpServletRequest request) {
		return funcInterface.login3rd(request);
	}

}
