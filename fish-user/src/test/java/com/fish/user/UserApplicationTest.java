package com.fish.user;

import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dayang
 */
@SpringBootTest
public class UserApplicationTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	void insertUserTest() {
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
		System.out.println(i);

	}

	@Test
	void deleteUserById() {
		int i = userMapper.deleteById(3);
		System.out.println(i);
	}

	@Test
	void demo01Test() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		List<User> userList = userMapper.selectByIds(list);
		userList.forEach(System.out::println);
	}

}
