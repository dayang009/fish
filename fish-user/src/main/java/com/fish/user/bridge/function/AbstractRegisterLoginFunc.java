package com.fish.user.bridge.function;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fish.common.core.exception.FishCloudException;
import com.fish.common.core.util.ResponseEnum;
import com.fish.user.entity.UserInfo;
import com.fish.user.mapper.UserInfoMapper;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

	/**
	 * protected 修饰方法，仅供子类使用
	 */
	protected String commonLogin(String account, String password, UserInfoMapper userInfoMapper) {
		LambdaQueryWrapper<UserInfo> userInfoWrapper = new LambdaQueryWrapper<>();
		userInfoWrapper.eq(UserInfo::getUserAccount, account);
		userInfoWrapper.eq(UserInfo::getUserPassword, password);
		UserInfo userInfo = userInfoMapper.selectOne(userInfoWrapper);
		if (userInfo != null) {
			return "login success";
		}
		else {
			return "login failed";
		}
	}

	protected String commonRegister(UserInfo userInfo, UserInfoMapper userInfoMapper) {
		boolean existsFlag = this.commonCheckUserExists(userInfo.getUserAccount(), userInfoMapper);
		if (existsFlag) {
			throw new FishCloudException(ResponseEnum.VALIDATE_ERROR, "用户已经存在");
		}
		userInfoMapper.insert(userInfo);
		return "register success";
	}

	protected boolean commonCheckUserExists(String username, UserInfoMapper userInfoMapper) {
		LambdaQueryWrapper<UserInfo> userInfoWrapper = new LambdaQueryWrapper<>();
		userInfoWrapper.eq(UserInfo::getUserAccount, username);
		return userInfoMapper.selectCount(userInfoWrapper) > 0;

	}

	@Override
	public String login(String account, String password) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String register(UserInfo userInfo) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean checkUserExists(String userName) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String login3rd(HttpServletRequest request) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
