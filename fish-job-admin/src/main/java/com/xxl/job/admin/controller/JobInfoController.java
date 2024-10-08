package com.xxl.job.admin.controller;

import com.fish.common.core.entity.XxlJobGroup;
import com.fish.common.core.entity.XxlJobInfo;
import com.fish.common.core.entity.XxlJobUser;
import com.fish.common.core.util.ReturnT;
import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.core.exception.XxlJobException;
import com.xxl.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.admin.core.scheduler.MisfireStrategyEnum;
import com.xxl.job.admin.core.scheduler.ScheduleTypeEnum;
import com.xxl.job.admin.core.thread.JobScheduleHelper;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.service.LoginService;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import com.xxl.job.core.util.DateUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * index controller
 *
 * @author xuxueli
 * @since 2015-12-19 16:13:16
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {

	private static final Logger logger = LoggerFactory.getLogger(JobInfoController.class);

	@Resource
	private XxlJobGroupDao xxlJobGroupDao;

	@Resource
	private XxlJobService xxlJobService;

	@RequestMapping
	public String index(HttpServletRequest request, Model model,
			@RequestParam(required = false, defaultValue = "-1") int jobGroup) {

		// 枚举-字典
		model.addAttribute("ExecutorRouteStrategyEnum", ExecutorRouteStrategyEnum.values()); // 路由策略-列表
		model.addAttribute("GlueTypeEnum", GlueTypeEnum.values()); // Glue类型-字典
		model.addAttribute("ExecutorBlockStrategyEnum", ExecutorBlockStrategyEnum.values()); // 阻塞处理策略-字典
		model.addAttribute("ScheduleTypeEnum", ScheduleTypeEnum.values()); // 调度类型
		model.addAttribute("MisfireStrategyEnum", MisfireStrategyEnum.values()); // 调度过期策略

		// 执行器列表
		List<XxlJobGroup> jobGroupListAll = xxlJobGroupDao.findAll();

		// filter group
		List<XxlJobGroup> jobGroupList = filterJobGroupByRole(request, jobGroupListAll);
		if (jobGroupList == null || jobGroupList.isEmpty()) {
			throw new XxlJobException(I18nUtil.getString("jobgroup_empty"));
		}

		model.addAttribute("JobGroupList", jobGroupList);
		model.addAttribute("jobGroup", jobGroup);

		return "jobinfo/jobinfo.index";
	}

	public static List<XxlJobGroup> filterJobGroupByRole(HttpServletRequest request,
			List<XxlJobGroup> jobGroupListAll) {
		List<XxlJobGroup> jobGroupList = new ArrayList<>();
		if (jobGroupListAll != null && !jobGroupListAll.isEmpty()) {
			XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
			if (loginUser.getRole() == 1) {
				jobGroupList = jobGroupListAll;
			}
			else {
				List<String> groupIdStrs = new ArrayList<>();
				if (loginUser.getPermission() != null && !loginUser.getPermission().trim().isEmpty()) {
					groupIdStrs = Arrays.asList(loginUser.getPermission().trim().split(","));
				}
				for (XxlJobGroup groupItem : jobGroupListAll) {
					if (groupIdStrs.contains(String.valueOf(groupItem.getId()))) {
						jobGroupList.add(groupItem);
					}
				}
			}
		}
		return jobGroupList;
	}

	public static void validPermission(HttpServletRequest request, int jobGroup) {
		XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
		if (!loginUser.validPermission(jobGroup)) {
			throw new RuntimeException(
					I18nUtil.getString("system_permission_limit") + "[username=" + loginUser.getUsername() + "]");
		}
	}

	@PermissionLimit(limit = false)
	@RequestMapping("/pageList")
	@ResponseBody
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length, int jobGroup, int triggerStatus,
			String jobDesc, String executorHandler, String author) {

		return xxlJobService.pageList(start, length, jobGroup, triggerStatus, jobDesc, executorHandler, author);
	}

	@PermissionLimit(limit = false)
	@RequestMapping("/add")
	@ResponseBody
	public ReturnT<String> add(XxlJobInfo jobInfo) {
		return xxlJobService.add(jobInfo);
	}

	@PermissionLimit(limit = false)
	@RequestMapping("/update")
	@ResponseBody
	public ReturnT<String> update(XxlJobInfo jobInfo) {
		return xxlJobService.update(jobInfo);
	}

	@PermissionLimit(limit = false)
	@RequestMapping("/remove")
	@ResponseBody
	public ReturnT<String> remove(int id) {
		return xxlJobService.remove(id);
	}

	@PermissionLimit(limit = false)
	@RequestMapping("/stop")
	@ResponseBody
	public ReturnT<String> pause(int id) {
		return xxlJobService.stop(id);
	}

	@PermissionLimit(limit = false)
	@RequestMapping("/start")
	@ResponseBody
	public ReturnT<String> start(int id) {
		return xxlJobService.start(id);
	}

	@PermissionLimit(limit = false)
	@RequestMapping("/trigger")
	@ResponseBody
	public ReturnT<String> triggerJob(HttpServletRequest request, int id, String executorParam, String addressList) {

		// login user
		XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
		// trigger
		return xxlJobService.trigger(loginUser, id, executorParam, addressList);
	}

	@RequestMapping("/nextTriggerTime")
	@ResponseBody
	public ReturnT<List<String>> nextTriggerTime(String scheduleType, String scheduleConf) {

		XxlJobInfo paramXxlJobInfo = new XxlJobInfo();
		paramXxlJobInfo.setScheduleType(scheduleType);
		paramXxlJobInfo.setScheduleConf(scheduleConf);

		List<String> result = new ArrayList<>();
		try {
			Date lastTime = new Date();
			for (int i = 0; i < 5; i++) {
				lastTime = JobScheduleHelper.generateNextValidTime(paramXxlJobInfo, lastTime);
				if (lastTime != null) {
					result.add(DateUtil.formatDateTime(lastTime));
				}
				else {
					break;
				}
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")) + e.getMessage());
		}
		return ReturnT.instance(result);

	}

}
