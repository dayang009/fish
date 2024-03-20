package com.fish.common.feign.client;

import com.fish.common.core.config.NotControllerResponseAdvice;
import com.fish.common.core.entity.XxlJobInfo;
import com.fish.common.core.util.ReturnT;
import com.fish.common.feign.constant.ServeConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * xxl-job 远程调用
 *
 * @author dayang
 * @see com.xxl.job.admin.controller
 */
@NotControllerResponseAdvice
@ResponseBody
@FeignClient(value = ServeConstant.XXL_JOB_SERVE, path = "/xxl-job-admin/jobinfo")
public interface JobInfoClient {

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#pageList(int, int, int, int, String, String, String)}
	 */
	@RequestMapping("/pageList")
	Map<String, Object> pageList(@RequestParam(name = "start", defaultValue = "0") int start,
			@RequestParam(name = "length", defaultValue = "10") int length, @RequestParam("jobGroup") int jobGroup,
			@RequestParam("triggerStatus") int triggerStatus, @RequestParam("jobDesc") String jobDesc,
			@RequestParam("executorHandler") String executorHandler, @RequestParam("author") String author);

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#add(XxlJobInfo)}
	 */
	@RequestMapping(path = "/add")
	ReturnT<String> add(@SpringQueryMap XxlJobInfo jobInfo);

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#update(XxlJobInfo)}
	 */
	@RequestMapping("/update")
	ReturnT<String> update(@SpringQueryMap XxlJobInfo jobInfo);

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#remove(int)}
	 */
	@RequestMapping("/remove")
	ReturnT<String> remove(Serializable id);

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#pause(int)}
	 */
	@RequestMapping("/stop")
	ReturnT<String> pause(Serializable id);

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#start(int)}
	 */
	@RequestMapping("/start")
	ReturnT<String> start(@RequestParam("id") Serializable id);

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#triggerJob(int, String, String)}
	 */
	@RequestMapping("/trigger")
	ReturnT<String> triggerJob(@RequestParam("id") Serializable id, @RequestParam("executorParam") String executorParam,
			@RequestParam("addressList") String addressList);

	/**
	 * {@link com.xxl.job.admin.controller.JobInfoController#nextTriggerTime(String, String)}
	 */
	@RequestMapping("/nextTriggerTime")
	ReturnT<List<String>> nextTriggerTime(@RequestParam("scheduleType") String scheduleType,
			@RequestParam("scheduleConf") String scheduleConf);

}
