package com.fish.user.bridge.function;

import com.fish.user.bridge.abst.factory.RegisterLoginComponentFactory;
import com.fish.user.entity.UserInfo;
import com.fish.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	@Resource
	private UserInfoMapper userInfoMapper;

	@PostConstruct
	private void initFuncMap() {
		RegisterLoginComponentFactory.funcMap.put("Default", this);
	}

	@Override
	public String login(String account, String password) {
		return super.commonLogin(account, password, userInfoMapper);
	}

	@Override
	public String register(UserInfo userInfo) {
		return super.commonRegister(userInfo, userInfoMapper);
	}

	@Override
	public boolean checkUserExists(String userName) {
		return super.commonCheckUserExists(userName, userInfoMapper);
	}

	// @Override
	// public String login3rd(HttpServletRequest request) {
	// return "";
	// }

	private void clearUserInfo(UserInfo user) {
		user.setId(null);
		user.setUserStatus(0);
		user.setCreateTime(null);
		user.setUpdateTime(null);
		user.setDeleteFlag(Boolean.FALSE);
	}

}
