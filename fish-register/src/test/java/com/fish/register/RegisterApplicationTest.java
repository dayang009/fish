package com.fish.register;

import com.fish.register.controller.MyController;
import com.fish.register.entity.MyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link org.junit.Test} 对应的是 Junit4，需要添加注解使用：@RunWith(SpringRunner.class) SpringBoot 2.4
 * 以上版本还需要手动添加 junit-vintage-engine 依赖
 * 如果{@link SpringBootTest}位置不和java包中结构对应，需要指定启动类： @SpringBootTest(classes =
 * RegisterApplication.class)
 *
 * @author dayang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegisterApplication.class)
public class RegisterApplicationTest {

	@Autowired
	private MyUser user;

	@Autowired
	private MyController myController;

	@Test
	public void myTest() {
		System.out.println("Hello");

		System.out.println(myController);
	}

}
