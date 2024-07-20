package com.fish.user.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.fish.common.core.config.NotControllerResponseAdvice;
import com.fish.user.config.LoginGiteaConfig;
import com.fish.user.config.LoginGiteeConfig;
import com.fish.user.entity.AuthPattern;
import com.fish.user.entity.UserInfo;
import com.fish.user.mapper.UserInfoMapper;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dayang
 * @since 2023/8/10
 */
@Tag(name = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

	@Resource
	private UserInfoMapper userInfoMapper;

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Resource
	private LoginGiteeConfig loginGiteeConfig;

	@Resource
	private LoginGiteaConfig loginGiteaConfig;

	@Resource
	private Gson gson;

	@PostMapping("/demo01")
	public Integer demo01(@RequestBody String id) {
		UserInfo user = new UserInfo();
		user.setId(id);
		user.setNickName("haha");
		user.setUserAccount("haha");
		user.setUserPassword("haha");
		int i = userInfoMapper.updateById(user);
		return i;
	}

	@GetMapping("/demo04")
	public UserInfo demo04() {
		UserInfo user = new UserInfo();
		user.setNickName("HelloWorld");
		user.setUserAccount("1729806750");
		user.setUserPassword("963258");
		user.setGender(0);
		user.setAge(10);
		user.setPhone("18855556666");
		user.setEmail("123@qq.com");
		user.setAdminFlag(0);
		userInfoMapper.insert(user);
		return user;
	}

	/**
	 * 应用通过 浏览器 或 Webview 将用户引导到码云三方认证页面上
	 */
	@NotControllerResponseAdvice
	@GetMapping("/gitee-login")
	public String giteeLogin() throws UnsupportedEncodingException {

		String redirectUri = URLEncoder.encode(loginGiteeConfig.getRedirectUri(), StandardCharsets.UTF_8.name());

		Map<String, Object> giteeReqMap = new HashMap<>();
		giteeReqMap.put(AuthPattern.CLIENT_ID, loginGiteeConfig.getClientId());
		giteeReqMap.put(AuthPattern.REDIRECT_URI, loginGiteeConfig.getRedirectUri());
		giteeReqMap.put(AuthPattern.RESPONSE_TYPE, loginGiteeConfig.getResponseType());
		giteeReqMap.put(AuthPattern.STATE, loginGiteeConfig.getState());

		String body = HttpRequest.get("https://gitee.com/oauth/authorize")
			.form(giteeReqMap)
			.setFollowRedirects(true)
			.execute()
			.body();

		// String s = HttpUtil.get("https://gitee.com/oauth/authorize", giteeReqMap);
		//
		System.out.println(body);
		return body;
	}

	@GetMapping("/gitea-login")
	public void giteaLogin() {

		Map<String, Object> giteeReqMap = new HashMap<>();
		giteeReqMap.put(AuthPattern.CLIENT_ID, loginGiteaConfig.getClientId());
		giteeReqMap.put(AuthPattern.REDIRECT_URI,
				URLEncoder.encode(loginGiteaConfig.getRedirectUri(), StandardCharsets.UTF_8));
		giteeReqMap.put(AuthPattern.RESPONSE_TYPE, loginGiteaConfig.getResponseType());
		giteeReqMap.put(AuthPattern.STATE, loginGiteaConfig.getState());

		String s = HttpUtil.get("http://dayang.icu:3000/login/oauth/authorize", giteeReqMap);
		System.out.println(s);

	}

	@NotControllerResponseAdvice
	@PostMapping("/demo05")
	public UserInfo demo05(@RequestBody UserInfo user) {
		System.out.println(gson.toJson(user));
		userInfoMapper.insert(user);
		return userInfoMapper.selectById(user.getId());
	}

}
