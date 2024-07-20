package com.fish.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fish.user.entity.UserInfo;

import java.util.List;

public interface UserInfoService extends IService<UserInfo> {

	List<UserInfo> searchUsersByTags(List<String> tags);

	/**
	 * 用户信息脱敏
	 * @param srcUser 原始用户信息
	 * @return 托敏后的数据
	 */
	UserInfo getSafetyUser(UserInfo srcUser);

}
