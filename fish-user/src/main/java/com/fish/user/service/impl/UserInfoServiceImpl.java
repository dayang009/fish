package com.fish.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fish.common.core.exception.FishCloudException;
import com.fish.common.core.util.ResponseEnum;
import com.fish.user.entity.UserInfo;
import com.fish.user.mapper.UserInfoMapper;
import com.fish.user.service.UserInfoService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

	@Resource
	private UserInfoMapper userInfoMapper;

	/**
	 * 用户登录功能
	 */
	public String login(String account, String password) {
		LambdaQueryWrapper<UserInfo> userInfoWrapper = new LambdaQueryWrapper<>();
		userInfoWrapper.eq(UserInfo::getUserAccount, account);
		userInfoWrapper.eq(UserInfo::getUserPassword, password);
		UserInfo user1 = userInfoMapper.selectOne(userInfoWrapper);
		if (user1 != null) {
			return "Login Success";
		}
		else {
			return "Login Failed";
		}
	}

	/**
	 * 用户注册功能
	 */
	public String register(UserInfo user) {
		if (this.checkUserExist(user.getUserAccount())) {
			throw new FishCloudException(ResponseEnum.USER_ERROR, "用户已存在");
		}
		this.clearUserInfo(user);
		userInfoMapper.insert(user);
		return "Register Success";

	}

	private void clearUserInfo(UserInfo user) {
		user.setId(null);
		user.setUserStatus(0);
		user.setCreateTime(null);
		user.setUpdateTime(null);
		user.setDeleteFlag(Boolean.FALSE);
	}

	public boolean checkUserExist(String account) {

		LambdaQueryWrapper<UserInfo> userInfoWrapper = new LambdaQueryWrapper<>();
		userInfoWrapper.eq(UserInfo::getUserAccount, account);
		return userInfoMapper.selectCount(userInfoWrapper) > 0;
	}

	@Override
	public List<UserInfo> searchUsersByTags(List<String> tags) {
		if (CollUtil.isEmpty(tags)) {
			throw new FishCloudException(ResponseEnum.APP_ERROR, "标签不能为空");
		}
		Gson gson = new Gson();
		List<UserInfo> users = this.list();
		return users.stream().filter(userInfo -> {
			TypeToken<Set<String>> tagType = new TypeToken<Set<String>>() {
			};
			Set<String> tagNameSet = gson.fromJson(gson.toJson(userInfo.getTags()), tagType.getType());
			tagNameSet = Optional.ofNullable(tagNameSet).orElse(new HashSet<>());
			for (String tag : tagNameSet) {
				return tagNameSet.contains(tag);
			}
			return false;
		}).map(this::getSafetyUser).collect(Collectors.toList());

	}

	@Override
	public UserInfo getSafetyUser(UserInfo srcUser) {
		return srcUser;
	}

}
