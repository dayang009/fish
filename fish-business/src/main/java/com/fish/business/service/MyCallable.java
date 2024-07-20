package com.fish.business.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private final Object object;

	public MyCallable(Object object) {
		this.object = object;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println(gson.toJson(object));
		System.out.println("异步任务比较耗时");
		Thread.sleep(2000L);
		return 88;
	}

}
