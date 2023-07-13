package com.fish.user.mapper;

import com.fish.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author dayang
 */
@SpringBootTest
public class UserMapperTest {

	@Resource
	private UserMapper userMapper;

	@Test
	void insert() {
		User user = new User();
		user.setNickName("zhangSan");
		user.setUserAccount("1729806");
		user.setUserPwd("123456");
		user.setGender(0);
		user.setAge(10);
		user.setPhone("1883522");
		user.setEmail("123@qq.com");
		user.setAdminFlag(0);
		int i = userMapper.insert(user);
		System.out.println("回填ID：" + user.getId());
		Assertions.assertEquals(1, i);
	}

	@Test
	void delete() {
	}

	@Test
	void deleteById() {
	}

	@Test
	void deleteByMap() {
	}

	@Test
	void updateById() {
	}

	@Test
	void selectById() {
	}

}