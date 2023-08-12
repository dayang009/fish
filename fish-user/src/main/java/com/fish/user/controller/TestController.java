package com.fish.user.controller;

import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dayang
 * @since 2023/8/10
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Resource
	private UserMapper userMapper;

	@PostMapping("/demo01")
	public Integer demo01(@RequestBody Integer id) {
		User user = new User();
		user.setId(id);
		user.setNickName("haha");
		user.setUserAccount("haha");
		user.setUserPwd("haha");

		int i = userMapper.updateById(user);
		return i;
	}

}
