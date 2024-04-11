package com.fish.business.controller;

import cn.hutool.core.date.DateUtil;
import com.fish.business.config.SchedulerConfig;
import com.fish.business.mvnentity.MirrorDTO;
import com.fish.business.mvnentity.ServerDTO;
import com.fish.business.mvnentity.SettingsRoot;
import com.fish.common.core.config.NotControllerResponseAdvice;
import com.fish.common.core.entity.Student;
import com.fish.common.core.entity.XxlJobInfo;
import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ReturnT;
import com.fish.common.core.util.YangUtil;
import com.fish.common.feign.client.JobInfoClient;
import com.fish.common.feign.client.UserClient;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
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

	@Resource
	private JobInfoClient jobInfoClient;

	@Resource
	private Gson gson;

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

	@Operation(summary = "测试 Feign 调用用户模块")
	@DeleteMapping("/demo06/{id}")
	public RespResult<?> demo06(@PathVariable(value = "id") String id) {
		return userClient.delete(id);
	}

	@NotControllerResponseAdvice
	@Operation(summary = "测试 Feign 调用 XXL-JOB 模块")
	@GetMapping("/demo07")
	public RespResult<?> demo07() {

		XxlJobInfo xxlJobInfo = new XxlJobInfo();
		// 执行器，只有一个写 1
		xxlJobInfo.setJobGroup(1);
		xxlJobInfo.setJobDesc("任务描述");
		xxlJobInfo.setAuthor("dayang");
		xxlJobInfo.setAlarmEmail("dayangtop@163.com");
		xxlJobInfo.setScheduleType("CRON");
		xxlJobInfo.setScheduleConf(YangUtil.dateToCron(DateUtil.offsetSecond(DateUtil.date(), 10)));
		/*
		 * DO_NOTHING ===> 任务过期了忽略，什么也不执行 FIRE_ONCE_NOW ===> 添加的任务已经过期，失火了，立即执行一次
		 */
		xxlJobInfo.setMisfireStrategy("FIRE_ONCE_NOW");
		// 路由策略，第一个 FIRST，有多个执行器的时候才生效
		xxlJobInfo.setExecutorRouteStrategy("FIRST");
		// JobHandler，执行器，任务的名称
		xxlJobInfo.setExecutorHandler("test");
		// 传入的参数
		xxlJobInfo.setExecutorParam("param1, param2");
		// 阻塞处理策略，单机串行 SERIAL_EXECUTION
		xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
		// 任务执行超时时间，单位秒，大于 0 时候生效
		xxlJobInfo.setExecutorTimeout(0);
		// 重试次数
		xxlJobInfo.setExecutorFailRetryCount(0);
		xxlJobInfo.setGlueType("BEAN");
		xxlJobInfo.setGlueSource("");
		// 备注
		xxlJobInfo.setGlueRemark("GLUE代码初始化");
		xxlJobInfo.setChildJobId("");
		// 是否启用
		xxlJobInfo.setTriggerStatus(0);
		xxlJobInfo.setTriggerLastTime(0L);
		xxlJobInfo.setTriggerNextTime(0L);

		ReturnT<String> add = jobInfoClient.add(xxlJobInfo);
		ReturnT<String> start = jobInfoClient.start(add.getContent());

		log.info("feign调用返回结果 ===> {}", add);
		return RespResult.success(start);
	}

	@GetMapping("/demo08")
	public void demo08(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();

		log.info("调用方地址 ===> {}", remoteAddr);
		List<Student> testList = this.getTestList();
		String json = gson.toJson(testList);

		List<Student> students = gson.fromJson(json, new TypeToken<List<Student>>() {
		}.getType());
		log.info(students.toString());

		// JsonArray jsonElements = gson.fromJson(json, JsonArray.class);
		// List<JsonObject> list = jsonElements.asList();
		// list.stream().map(new Function<JsonElement, String>() {
		// @Override
		// public String apply(JsonElement jsonElement) {
		// return jsonElement.;
		// }
		// });

	}

	@GetMapping("/demo09")
	public void demo09(HttpServletRequest request) {
		SettingsRoot settingsRoot = new SettingsRoot();
		settingsRoot.setLocalRepository("");
		settingsRoot.setInteractiveMode("");
		settingsRoot.setOffline("");
		settingsRoot.setPluginGroup(new ArrayList<String>());
		settingsRoot.setServerDTOList(new ArrayList<ServerDTO>());
		settingsRoot.setMirrorDTOList(new ArrayList<MirrorDTO>());
		settingsRoot.setNowDate(new Date());
		settingsRoot.setEndTime(LocalDateTime.now());

	}

	private List<Student> getTestList() {
		Student s1 = new Student();
		s1.setId("111");
		s1.setNickName("zhangSan");
		s1.setUserAccount("aaabbb");
		s1.setUserPwd("123456");
		s1.setGender(0);
		s1.setAge(18);
		s1.setPhone("18855554444");
		s1.setEmail("123@qq.com");
		s1.setAdminFlag(0);
		s1.setCreateTime(LocalDateTime.now());
		s1.setUpdateTime(LocalDateTime.now());
		s1.setDeleteFlag(false);

		Student s2 = new Student();
		s2.setId("222");
		s2.setNickName("liSi");
		s2.setUserAccount("85214adf24");
		s2.setUserPwd("963258");
		s2.setGender(1);
		s2.setAge(88);
		s2.setPhone("19955557741");
		s2.setEmail("147258@qq.com");
		s2.setAdminFlag(1);
		s2.setCreateTime(LocalDateTime.now());
		s2.setUpdateTime(LocalDateTime.now());
		s2.setDeleteFlag(false);

		Student s3 = new Student();
		s3.setId("333");
		s3.setNickName("");
		s3.setUserAccount("");
		s3.setUserPwd("");
		s3.setGender(0);
		s3.setAge(0);
		s3.setPhone("19955552222");
		s3.setEmail("hahah@163.com");
		s3.setAdminFlag(0);
		s3.setCreateTime(LocalDateTime.now());
		s3.setUpdateTime(LocalDateTime.now());
		s3.setDeleteFlag(true);

		List<Student> students = new ArrayList<>();
		students.add(s1);
		students.add(s2);
		students.add(s3);
		return students;
	}

}
