package com.fish.user.controller;

import com.fish.user.entity.UserInfo;
import com.fish.user.mapper.UserInfoMapper;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mybatis.spring.SqlSessionTemplate;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	private Gson gson;

	@Resource
	private RedissonClient redissonClient;

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
		user.setTags(Collections.singletonMap("zhangSan", "liSi"));
		user.setRoles(Collections.singletonList("admin"));
		userInfoMapper.insert(user);
		return user;
	}

	@GetMapping("/demo05")
	public void demo05() {

		List<UserInfo> lists = new ArrayList<>(100);

		for (int i = 0; i < 1000; i++) {
			UserInfo user = new UserInfo();
			user.setNickName("HelloWorld");
			user.setUserAccount("1729806750");
			user.setUserPassword("963258");
			user.setGender(0);
			user.setAge(10);
			user.setPhone("18855556666");
			user.setEmail("123@qq.com");
			user.setTags(Collections.singletonMap("zhangSan", "liSi"));
			lists.add(user);
		}
		userInfoMapper.insert(lists);
	}

	@GetMapping("/demo06")
	public void demo06() {
	}

}
