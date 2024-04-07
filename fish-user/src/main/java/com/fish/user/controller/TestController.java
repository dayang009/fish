package com.fish.user.controller;

import com.fish.user.entity.Restaurant;
import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
	private UserMapper userMapper;

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@PostMapping("/demo01")
	public Integer demo01(@RequestBody String id) {
		User user = new User();
		user.setId(id);
		user.setNickName("haha");
		user.setUserAccount("haha");
		user.setUserPwd("haha");

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
		user.setUserPwd("963258");
		user.setGender(0);
		user.setAge(10);
		user.setPhone("18855556666");
		user.setEmail("123@qq.com");
		user.setAdminFlag(0);
		Restaurant restaurant1 = new Restaurant("666", "数字谷", "北京", new Date());
		Restaurant restaurant2 = new Restaurant("888", "数字谷", "上海", new Date());
		user.setRestaurant(Lists.newArrayList(restaurant1, restaurant2));
		userMapper.insert(user);
		return user;
	}

}
