package com.fish.business.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

/**
 * 定时任务
 *
 * @author dayang
 */
public class DingShi implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.initialize();
		taskScheduler.setPoolSize(5);
		System.out.println(taskScheduler.getActiveCount());

		taskScheduler.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello");
			}
		}, new Date());

		// CronExpression cronExpression = new CronExpression();

	}

}
