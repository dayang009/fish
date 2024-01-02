package com.fish.business.controller;

import cn.hutool.core.date.DateUtil;
import com.fish.business.config.SchedulerConfig;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@RestController
public class TestController {

	@Resource
	private ThreadPoolTaskScheduler poolTaskScheduler;

	@GetMapping("/demo01")
	public void demo01(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
		ScheduledFuture<?> schedule = poolTaskScheduler.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("模拟资源接口调用" + DateUtil.now());
			}
		}, date);
		SchedulerConfig.cache.put("ka-" + DateUtil.date(date), schedule);

	}

	@GetMapping("/demo02")
	public void demo02(@RequestParam String key) {
		ScheduledFuture future = SchedulerConfig.cache.get(key);
		if (future != null) {
			// 指定 ScheduledFuture 来停止当前线程
			future.cancel(true);
			// 移除缓存
			SchedulerConfig.cache.remove(key);
		}
	}

}
