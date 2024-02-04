package com.fish.business.controller;

import cn.hutool.core.date.DateUtil;
import com.fish.business.config.SchedulerConfig;
import com.fish.common.core.config.NotControllerResponseAdvice;
import com.fish.common.core.util.RespResult;
import com.fish.common.feign.client.UserClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Tag(name = "测试控制参数")
@RefreshScope
@Slf4j
@EnableScheduling
@RestController
public class TestController {

	@Resource
	private SchedulerConfig schedulerConfig;

	@Resource
	private UserClient userClient;

	@Operation(summary = "添加一个定时任务")
	@GetMapping("/demo01")
	public void demo01(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
		ScheduledFuture<?> schedule = schedulerConfig.taskScheduler().schedule(() -> {
			System.out.println("模拟资源接口调用" + DateUtil.now());
			log.info("模拟定时任务触发");
		}, date);
		SchedulerConfig.cache.put(DateUtil.date(date).toString(), schedule);

	}

	@Operation(summary = "删除已设置的定时任务")
	@GetMapping("/demo02")
	public void demo02(@RequestParam String key) {
		ScheduledFuture<?> future = SchedulerConfig.cache.get(key);
		if (future != null) {
			// 指定 ScheduledFuture 来停止当前线程
			future.cancel(true);
			System.out.println("任务删除成功" + DateUtil.now());
			log.info("任务删除成功");
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

	@NotControllerResponseAdvice
	@Operation(summary = "测试Feign调用用户模块")
	@GetMapping("/demo05")
	public String demo05() {
		return userClient.demo01();
	}

	@Operation(summary = "测试Feign调用用户模块")
	@DeleteMapping("/demo06/{id}")
	public RespResult<?> demo06(@PathVariable(value = "id") String id) {
		return userClient.delete(id);
	}

}
