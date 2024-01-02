package com.fish.business.temp;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class Diaodu {

	public static void main(String[] args) {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.initialize();
		taskScheduler.setPoolSize(5);
		System.out.println(taskScheduler);

		taskScheduler.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello");
			}
		}, new CronTrigger("0/5 * * * * ?"));

		// CronExpression cronExpression = new CronExpression();
	}

}
