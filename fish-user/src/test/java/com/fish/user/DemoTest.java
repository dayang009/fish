package com.fish.user;

import com.fish.user.entity.Restaurant;
import com.fish.user.entity.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

public class DemoTest {

	@Test
	void demo01() {
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

		Optional<User> user1 = Optional.ofNullable(user);
		user1.ifPresent(user2 -> System.out.println(user2.getPhone()));
		user1.filter(user3 -> user3.getAge() > 5);

		User user5 = new User();
	}

}
