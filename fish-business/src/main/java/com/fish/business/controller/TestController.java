package com.fish.business.controller;

import cn.hutool.core.date.DateUtil;
import com.fish.business.config.SchedulerConfig;
import com.fish.business.entity.Stud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Tag(name = "测试控制参数")
@RefreshScope
@EnableScheduling
@RestController
public class TestController {

	@Resource
	private ThreadPoolTaskScheduler poolTaskScheduler;

	@Operation(summary = "添加一个定时任务")
	@GetMapping("/demo01")
	public void demo01(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
		ScheduledFuture<?> schedule = poolTaskScheduler.schedule(() -> System.out.println("模拟资源接口调用" + DateUtil.now()),
				date);
		SchedulerConfig.cache.put("ka-" + DateUtil.date(date), schedule);

	}

	@Operation(summary = "删除已设置的定时任务")
	@GetMapping("/demo02")
	public void demo02(@RequestParam String key) {
		ScheduledFuture<?> future = SchedulerConfig.cache.get(key);
		if (future != null) {
			// 指定 ScheduledFuture 来停止当前线程
			future.cancel(true);
		}
	}

	@GetMapping("/demo03")
	public Map<String, String> demo03() {
		System.out.println("模拟资源接口调用" + DateUtil.now());
		Map<String, String> map = new HashMap<>();
		map.put("name", "zhangSan");
		return map;
	}

	@Operation(summary = "查看存储定时任务的Map")
	@GetMapping("/demo04")
	public Map<String, ScheduledFuture<?>> demo04() {
		return SchedulerConfig.cache;
	}

	@PostMapping("/demo05")
	public Stud demo05() {
		Stud stud = new Stud();
		stud.setId("88");
		stud.setName("zhangSan");
		stud.setStartTime(LocalDateTime.now());
		stud.setEndTime(LocalDateTime.now());
		System.out.println(">>>" + stud);
		return stud;
	}

}
