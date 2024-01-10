package com.fish.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Configuration
public class SchedulerConfig {

	public static Map<String, ScheduledFuture<?>> cache = Collections.synchronizedMap(new ScheduCacheMap<>());

	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
		poolTaskScheduler.setPoolSize(10);
		poolTaskScheduler.setThreadNamePrefix("DownDown-");
		poolTaskScheduler.setAwaitTerminationSeconds(60);
		poolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
		return poolTaskScheduler;
	}

}
