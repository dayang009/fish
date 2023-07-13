package com.fish.user.controller;

import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dayang
 */
@RestController
public class UserController {

	@Resource
	private UserMapper userMapper;

	@GetMapping("/user1")
	public String demo01() {

		User user = new User();
		user.setNickName("zhangSan");
		user.setUserAccount("qwer");
		user.setUserPwd("123456");
		user.setGender(0);
		user.setAge(10);
		user.setPhone("188555588");
		user.setEmail("123@qq.com");
		user.setAdminFlag(0);

		int insert = userMapper.insert(user);
		return String.valueOf(insert);
	}

	@GetMapping("/demo02")
	public List<User> demo02() {
		PageHelper.startPage(1, 2, false);
		List<User> userList = userMapper.findAll();
		userList.forEach(System.out::println);
		return userList;
	}

}
