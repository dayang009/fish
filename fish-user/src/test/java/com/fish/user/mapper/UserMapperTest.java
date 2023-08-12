package com.fish.user.mapper;

import com.fish.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * @author dayang
 */
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
public class UserMapperTest {

	@Resource
	private UserMapper userMapper;

	@DisplayName("插入一条数据")
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
		User user = new User();
		user.setId(4);
		user.setNickName("haha");
		user.setUserAccount("haha");
		user.setUserPwd("haha");

		int i = userMapper.updateById(user);
		System.out.println(i);
	}

	@Test
	void selectById() {
	}

}
