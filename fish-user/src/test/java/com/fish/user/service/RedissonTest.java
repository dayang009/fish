package com.fish.user.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@Slf4j
@SpringBootTest
public class RedissonTest {

	@Resource
	private RedissonClient redisson;

	@Test
	void testRedisson01() {
		// 3. Get Redis based implementation of java.util.concurrent.ConcurrentMap

		RLock lock = redisson.getLock("myMap");
		try {
			if (lock.tryLock()) {
				Thread.sleep(1000000L);
				System.out.println("getLock: " + lock.isLocked() + Thread.currentThread().getName());
			}
		}
		catch (InterruptedException e) {
			log.error(e.getMessage());
		}
		finally {
			// 只能释放自己的锁
			if (lock.isHeldByCurrentThread()) {
				System.out.println("unLock: " + lock.isLocked() + Thread.currentThread().getName());
				lock.unlock();
			}
		}

	}

	@Test
	void testRedisson02() {
		RList<Object> testList = redisson.getList("testList");
		testList.add("ceshi测试");
		testList.add(5588);
		testList.add(new Object());
		testList.expire(Duration.ofSeconds(30L));
	}

}
