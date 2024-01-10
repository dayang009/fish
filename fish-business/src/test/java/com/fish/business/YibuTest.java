package com.fish.business;

import com.fish.business.service.MyCallable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class YibuTest {

	@Test
	public void demo01() throws ExecutionException, InterruptedException, TimeoutException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		System.out.println("Hello");
		MyCallable callable = new MyCallable();
		Future<Integer> submit = executorService.submit(callable);
		System.out.println("最终输出 >>> " + submit.get(1L, TimeUnit.SECONDS));

	}

}
