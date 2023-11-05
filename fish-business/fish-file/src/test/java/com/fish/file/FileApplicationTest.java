package com.fish.file;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author dayang
 * @since 2023/10/17
 */
@SpringBootTest
public class FileApplicationTest {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void demo01Test() {

		stringRedisTemplate.opsForValue().set("uuid", "dayang", 2, TimeUnit.DAYS);

	}

}
