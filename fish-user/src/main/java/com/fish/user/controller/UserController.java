package com.fish.user.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fish.common.core.constant.GlobalConstant;
import com.fish.common.core.exception.FishCloudException;
import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ResponseEnum;
import com.fish.user.common.ListResult;
import com.fish.user.entity.UserInfo;
import com.fish.user.entity.UserInfoReq;
import com.fish.user.mapper.UserInfoMapper;
import com.fish.user.service.UserInfoService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author dayang
 */
@Tag(name = "用户接口")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserInfoMapper userInfoMapper;

	@Resource
	private Gson gson;

	@PostMapping("/login")
	public RespResult<?> login(@RequestBody @Validated UserInfo user) {
		log.info("input account: {}, password: {}", user.getUserAccount(), user.getUserPassword());

		LambdaQueryWrapper<UserInfo> userInfoWrapper = new LambdaQueryWrapper<>();
		userInfoWrapper.eq(UserInfo::getUserAccount, user.getUserAccount());
		userInfoWrapper.eq(UserInfo::getUserPassword, user.getUserPassword());
		UserInfo userInfo;
		try {
			userInfo = userInfoMapper.userLogin(user);
			Assert.notNull(userInfo, "查询出来的用户为空，抛异常");
		}
		catch (Exception e) {
			throw new FishCloudException(ResponseEnum.APP_ERROR, "用户名和密码不匹配");
		}

		JWTCreator.Builder builder = JWT.create();
		builder.withClaim("id", userInfo.getId());
		builder.withClaim("nickName", userInfo.getNickName());
		Object tagsObj = userInfo.getTags();

		if (tagsObj instanceof List<?>) {
			builder.withClaim("tags", (List<?>) tagsObj);
		}
		if (tagsObj instanceof Map<?, ?>) {
			builder.withClaim("tags", (Map<String, ?>) tagsObj);
		}

		// 整合过期时间
		builder.withExpiresAt(DateUtil.offsetDay(new Date(), 2));

		// 生成Token
		String sign = builder.sign(Algorithm.HMAC256(GlobalConstant.SALT));
		return RespResult.success(Collections.singletonMap("jwt", sign));
	}

	@GetMapping("/me")
	public UserInfo me(HttpServletRequest request) {
		String authorization = request.getHeader("authorization");
		if (StrUtil.isEmpty(authorization)) {
			throw new FishCloudException(ResponseEnum.TOKEN_PARSE_ERROR, "认证失败");
		}
		String token = authorization.split(" ")[1];

		DecodedJWT verify = JWT.require(Algorithm.HMAC256(GlobalConstant.SALT)).build().verify(token);
		String id = verify.getClaim("id").asString();
		String nickName = verify.getClaim("nickName").asString();
		UserInfo userInfo = userInfoService.getById(id);

		userInfo.setUserPassword(null);

		return userInfo;

	}

	@PostMapping("/register")
	public RespResult<?> register(@RequestBody @Validated UserInfo user, HttpServletRequest request) {
		log.info("注册接口被调用的IP: {}", ServletUtil.getClientIP(request));
		if (StrUtil.isAllEmpty(user.getEmail(), user.getPhone())) {
			throw new FishCloudException(ResponseEnum.USER_NULL_PARAS_ERROR, "注册请输入邮箱或者手机号");
		}
		user.setId(null);
		user.setUserAccount(RandomUtil.randomString(6));
		try {
			userInfoService.save(user);
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new FishCloudException(ResponseEnum.BAD_SQL_GRAMMAR, "用户注册失败");
		}
		return RespResult.instance(ResponseEnum.SUCCESS.getCode(), "注册成功", user.getUserAccount());
	}

	@PostMapping("/list")
	public ListResult<UserInfo> userList(@RequestBody UserInfoReq userInfoReq, HttpServletRequest request) {

		Page<UserInfo> page = userInfoService.page(PageDTO.of(userInfoReq.getCurrent(), userInfoReq.getSize()));

		List<UserInfo> list = page.getRecords();

		ListResult<UserInfo> listResult = new ListResult<>();
		listResult.setData(list);
		listResult.setTotal(page.getTotal());
		listResult.setCurrent(userInfoReq.getCurrent());
		listResult.setSize(userInfoReq.getSize());

		return listResult;
	}

	/**
	 * 插入一条数据
	 */
	@PostMapping("")
	public RespResult<?> save(@RequestBody @Validated UserInfo user) {
		user.setId(null);
		user.setCreateTime(null);
		user.setDeleteFlag(Boolean.FALSE);
		userInfoService.save(user);
		return RespResult.success(user);
	}

	@DeleteMapping("/{id}")
	public RespResult<?> delete(@PathVariable Serializable id, UserInfo userInfo) {
		log.info(gson.toJson(userInfo));
		userInfoMapper.deleteById(id);
		return RespResult.success("用户删除成功");
	}

	@PutMapping("/")
	public RespResult<?> update(@RequestBody @Validated UserInfo user) {
		int i = userInfoService.getBaseMapper().updateById(user);
		if (i == 0) {
			throw new FishCloudException(ResponseEnum.USER_ERROR, "原用户不存在");
		}
		return RespResult.success("用户修改成功");
	}

	@GetMapping("/{id}")
	public RespResult<UserInfo> getById(@PathVariable Serializable id) {
		UserInfo user = userInfoService.getById(id);
		if (user == null) {
			throw new FishCloudException(ResponseEnum.USER_REQ_PARAS_ERROR, "无法通过此ID查到用户");
		}
		return RespResult.success(user);
	}

}
