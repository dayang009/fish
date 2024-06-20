package com.fish.user.controller;

import cn.hutool.http.HttpUtil;
import com.fish.common.core.config.NotControllerResponseAdvice;
import com.fish.user.config.LoginGiteeConfig;
import com.fish.user.entity.Restaurant;
import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author dayang
 * @since 2023/8/10
 */
@Tag(name = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController {

	@Resource
	private UserMapper userMapper;

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Resource
	private LoginGiteeConfig loginGiteeConfig;

	@Resource
	private Gson gson;

	@PostMapping("/demo01")
	public Integer demo01(@RequestBody String id) {
		User user = new User();
		user.setId(id);
		user.setNickName("haha");
		user.setUserAccount("haha");
		user.setUserPassword("haha");

		int i = userMapper.updateById(user);
		return i;
	}

	@GetMapping("/demo02")
	public List<User> demo02() {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		List<User> users;
		try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
			UserMapper uMapper = sqlSession.getMapper(UserMapper.class);
			users = uMapper.selectByIds(Lists.newArrayList(1, 2, 3, 4));
			users.forEach(System.out::println);
			uMapper.insertBatchSomeColumn(users);
			sqlSession.commit();
			// 清理缓存，防止溢出
			sqlSession.clearCache();
		}
		return users;

	}

	@GetMapping("/demo03")
	public int demo03() {
		User user1 = new User("zhangSan", "qwer", "123456", 1, 18);
		User user2 = new User("liSi", "asdf", "654321", 0, 80);
		List<User> userList = new ArrayList<>();
		Collections.addAll(userList, user1, user2);
		return userMapper.insertBatchSomeColumn(userList);
	}

	@GetMapping("/demo04")
	public User demo04() {
		User user = new User();
		user.setNickName("HelloWorld");
		user.setUserAccount("1729806750");
		user.setUserPassword("963258");
		user.setGender(0);
		user.setAge(10);
		user.setPhone("18855556666");
		user.setEmail("123@qq.com");
		user.setAdminFlag(0);
		Restaurant restaurant1 = new Restaurant("666", "数字谷", "北京", LocalDateTime.now());
		Restaurant restaurant2 = new Restaurant("888", "数字谷", "上海", LocalDateTime.now());
		user.setRestaurant(Lists.newArrayList(restaurant1, restaurant2));
		userMapper.insert(user);
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
		giteeReqMap.put("client_id", loginGiteeConfig.getClientId());
		giteeReqMap.put("redirect_uri", redirectUri);
		giteeReqMap.put("response_type", loginGiteeConfig.getResponseType());
		giteeReqMap.put("state", loginGiteeConfig.getState());

		String s = HttpUtil.get("https://gitee.com/oauth/authorize", giteeReqMap);
		return s;
	}

	@NotControllerResponseAdvice
	@PostMapping("/demo05")
	public User demo05(@RequestBody User user) {
		System.out.println(gson.toJson(user));
		return user;
	}

}
