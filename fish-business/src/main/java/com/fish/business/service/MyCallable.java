package com.fish.business.service;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {

		System.out.println("异步任务比较耗时");
		Thread.sleep(2000L);
		return 88;
	}

}
