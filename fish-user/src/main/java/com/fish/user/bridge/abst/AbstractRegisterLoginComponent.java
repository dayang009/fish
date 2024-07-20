package com.fish.user.bridge.abst;

import com.fish.user.bridge.function.RegisterLoginFuncInterface;
import com.fish.user.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractRegisterLoginComponent implements RegisterLoginFuncInterface {

	/**
	 * 面向接口编程，引入属性是桥接模式中的桥
	 */
	protected RegisterLoginFuncInterface funcInterface;

	/**
	 * 通过有参构造函数，初始化接口属性
	 * @param funcInterface 桥接口
	 */
	public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
		validate(funcInterface);
		this.funcInterface = funcInterface;
	}

	/**
	 * 校验参数类型不为 null。final方法，不允许子类重写
	 * @param funcInterface 桥接口
	 */
	protected final void validate(RegisterLoginFuncInterface funcInterface) {
		if (funcInterface == null) {
			throw new UnsupportedOperationException("Unknown register/login function type!");
		}
	}

	@Override
	public abstract String login(String username, String password);

	@Override
	public abstract String register(UserInfo userInfo);

	@Override
	public abstract boolean checkUserExists(String userName);

	@Override
	public abstract String login3rd(HttpServletRequest request);

}
