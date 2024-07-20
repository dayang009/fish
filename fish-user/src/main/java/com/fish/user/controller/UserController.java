package com.fish.user.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.fish.common.core.exception.FishCloudException;
import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ResponseEnum;
import com.fish.user.adapter.Login3rdAdapter;
import com.fish.user.entity.UserInfo;
import com.fish.user.mapper.UserInfoMapper;
import com.fish.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author dayang
 */
@Tag(name = "用户接口")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	// @Resource(name = "UserInfoServiceImpl")
	private UserInfoService userInfoService;

	@Resource
	private UserInfoMapper userInfoMapper;

	@Resource
	private Login3rdAdapter login3rdAdapter;

	@PostMapping("/login")
	public RespResult<?> login(@RequestBody @Validated UserInfo user) {
		// String login = userInfoService.login(user.getUserAccount(),
		// user.getUserPassword());
		return RespResult.success("login");
	}

	@PostMapping("/register")
	public RespResult<?> register(@RequestBody @Validated UserInfo user, HttpServletRequest request) {
		log.info("注册接口被调用的IP: {}", ServletUtil.getClientIP(request));
		// String register = userInfoService.register(user);
		return RespResult.success("register");
	}

	/**
	 * Gitee平台回调接口，携带参数返回
	 */
	@GetMapping("/gitee")
	public RespResult<?> gitee(String code, String state, HttpServletRequest servletRequest) {
		log.info("Gitee平台回调接口IP ==> {}", ServletUtil.getClientIP(servletRequest));
		String s = login3rdAdapter.loginByGitee(code, state);
		return RespResult.success(s);
	}

	@GetMapping("/gitea")
	public RespResult<?> gitea(Object object) {
		System.out.println(object);
		return RespResult.success();
	}

	@GetMapping("/github")
	public RespResult<?> github(String code, String state, HttpServletRequest servletRequest) {
		String s = login3rdAdapter.loginByGitee(code, state);
		return RespResult.success(s);
	}

	@PostMapping("")
	public RespResult<?> save(@RequestBody @Validated UserInfo user) {
		userInfoMapper.insert(user);
		return RespResult.success(user);
	}

	@DeleteMapping("/{id}")
	public RespResult<?> delete(@PathVariable Serializable id) {
		userInfoMapper.deleteById(id);
		return RespResult.success("用户删除成功");
	}

	@PutMapping("/")
	public RespResult<?> update(@RequestBody @Validated UserInfo user) {
		int i = userInfoMapper.updateById(user);
		if (i == 0) {
			throw new FishCloudException(ResponseEnum.USER_ERROR, "原用户不存在");
		}
		return RespResult.success("用户修改成功");
	}

	@GetMapping("/{id}")
	public RespResult<UserInfo> getById(@PathVariable Serializable id) {
		UserInfo user = userInfoMapper.selectById(id);
		if (user == null) {
			throw new FishCloudException(ResponseEnum.USER_REQ_PARAS_ERROR, "无法通过此ID查到用户");
		}
		return RespResult.success(user);
	}

}
