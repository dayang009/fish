package com.fish.common.core.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseThreadPool {

	// 定义全局线程池
	private static final ExecutorService GLOBAL_DB_POOL = Executors.newFixedThreadPool(10);

}
