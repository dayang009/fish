package com.xxl.job.admin.service.impl;

import com.fish.common.core.entity.XxlJobGroup;
import com.fish.common.core.entity.XxlJobInfo;
import com.fish.common.core.entity.XxlJobLogReport;
import com.fish.common.core.entity.XxlJobUser;
import com.fish.common.core.util.ReturnT;
import com.xxl.job.admin.core.cron.CronExpression;
import com.xxl.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.admin.core.scheduler.MisfireStrategyEnum;
import com.xxl.job.admin.core.scheduler.ScheduleTypeEnum;
import com.xxl.job.admin.core.thread.JobScheduleHelper;
import com.xxl.job.admin.core.thread.JobTriggerPoolHelper;
import com.xxl.job.admin.core.trigger.TriggerTypeEnum;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.dao.*;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import com.xxl.job.core.util.DateUtil;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;

/**
 * core job action for xxl-job
 *
 * @author xuxueli
 * @since 2016-5-28 15:30:33
 */
@Service
public class XxlJobServiceImpl implements XxlJobService {

	private static final Logger logger = LoggerFactory.getLogger(XxlJobServiceImpl.class);

	@Resource
	public XxlJobLogDao xxlJobLogDao;

	@Resource
	private XxlJobGroupDao xxlJobGroupDao;

	@Resource
	private XxlJobInfoDao xxlJobInfoDao;

	@Resource
	private XxlJobLogGlueDao xxlJobLogGlueDao;

	@Resource
	private XxlJobLogReportDao xxlJobLogReportDao;

	@Override
	public Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc,
			String executorHandler, String author) {

		// page list
		List<XxlJobInfo> list = xxlJobInfoDao.pageList(start, length, jobGroup, triggerStatus, jobDesc, executorHandler,
				author);
		int list_count = xxlJobInfoDao.pageListCount(start, length, jobGroup, triggerStatus, jobDesc, executorHandler,
				author);

		// package result
		Map<String, Object> maps = new HashMap<>();
		maps.put("recordsTotal", list_count); // 总记录数
		maps.put("recordsFiltered", list_count); // 过滤后的总记录数
		maps.put("data", list); // 分页列表
		return maps;
	}

	@Override
	public ReturnT<String> add(XxlJobInfo jobInfo) {

		// valid base
		XxlJobGroup group = xxlJobGroupDao.load(jobInfo.getJobGroup());
		if (group == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
		}
		if (jobInfo.getJobDesc() == null || jobInfo.getJobDesc().trim().isEmpty()) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobdesc")));
		}
		if (jobInfo.getAuthor() == null || jobInfo.getAuthor().trim().isEmpty()) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_author")));
		}

		// valid trigger
		ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(jobInfo.getScheduleType(), null);
		if (scheduleTypeEnum == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
		}
		if (scheduleTypeEnum == ScheduleTypeEnum.CRON) {
			if (jobInfo.getScheduleConf() == null || !CronExpression.isValidExpression(jobInfo.getScheduleConf())) {
				return ReturnT.instance(ReturnT.FAIL_CODE, "Cron" + I18nUtil.getString("system_unvalid"));
			}
		}
		else if (scheduleTypeEnum == ScheduleTypeEnum.FIX_RATE) {
			if (jobInfo.getScheduleConf() == null) {
				return ReturnT.instance(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type")));
			}
			try {
				int fixSecond = Integer.parseInt(jobInfo.getScheduleConf());
				if (fixSecond < 1) {
					return ReturnT.instance(ReturnT.FAIL_CODE,
							(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
				}
			}
			catch (Exception e) {
				return ReturnT.instance(ReturnT.FAIL_CODE,
						(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
			}
		}

		// valid job
		if (GlueTypeEnum.match(jobInfo.getGlueType()) == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_gluetype") + I18nUtil.getString("system_unvalid")));
		}
		if (GlueTypeEnum.BEAN == GlueTypeEnum.match(jobInfo.getGlueType())
				&& (jobInfo.getExecutorHandler() == null || jobInfo.getExecutorHandler().trim().isEmpty())) {
			return ReturnT.instance(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + "JobHandler"));
		}
		// 》fix "\r" in shell
		if (GlueTypeEnum.GLUE_SHELL == GlueTypeEnum.match(jobInfo.getGlueType()) && jobInfo.getGlueSource() != null) {
			jobInfo.setGlueSource(jobInfo.getGlueSource().replaceAll("\r", ""));
		}

		// valid advanced
		if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_executorRouteStrategy") + I18nUtil.getString("system_unvalid")));
		}
		if (MisfireStrategyEnum.match(jobInfo.getMisfireStrategy(), null) == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("misfire_strategy") + I18nUtil.getString("system_unvalid")));
		}
		if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_executorBlockStrategy") + I18nUtil.getString("system_unvalid")));
		}

		// 》ChildJobId valid
		if (jobInfo.getChildJobId() != null && !jobInfo.getChildJobId().trim().isEmpty()) {
			String[] childJobIds = jobInfo.getChildJobId().split(",");
			for (String childJobIdItem : childJobIds) {
				if (childJobIdItem != null && !childJobIdItem.trim().isEmpty() && isNumeric(childJobIdItem)) {
					XxlJobInfo childJobInfo = xxlJobInfoDao.loadById(Integer.parseInt(childJobIdItem));
					if (childJobInfo == null) {
						return ReturnT.instance(ReturnT.FAIL_CODE,
								MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})"
										+ I18nUtil.getString("system_not_found")), childJobIdItem));
					}
				}
				else {
					return ReturnT.instance(ReturnT.FAIL_CODE,
							MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})"
									+ I18nUtil.getString("system_unvalid")), childJobIdItem));
				}
			}

			// join , avoid "xxx,,"
			StringBuilder temp = new StringBuilder();
			for (String item : childJobIds) {
				temp.append(item).append(",");
			}
			temp = new StringBuilder(temp.substring(0, temp.length() - 1));

			jobInfo.setChildJobId(temp.toString());
		}

		// add in db
		jobInfo.setAddTime(new Date());
		jobInfo.setUpdateTime(new Date());
		jobInfo.setGlueUpdatetime(new Date());
		xxlJobInfoDao.save(jobInfo);
		if (jobInfo.getId() < 1) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_add") + I18nUtil.getString("system_fail")));
		}

		return ReturnT.instance(String.valueOf(jobInfo.getId()));
	}

	private boolean isNumeric(String str) {
		try {
			int result = Integer.parseInt(str);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public ReturnT<String> update(XxlJobInfo jobInfo) {

		// valid base
		if (jobInfo.getJobDesc() == null || jobInfo.getJobDesc().trim().isEmpty()) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobdesc")));
		}
		if (jobInfo.getAuthor() == null || jobInfo.getAuthor().trim().isEmpty()) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_author")));
		}

		// valid trigger
		ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(jobInfo.getScheduleType(), null);
		if (scheduleTypeEnum == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
		}
		if (scheduleTypeEnum == ScheduleTypeEnum.CRON) {
			if (jobInfo.getScheduleConf() == null || !CronExpression.isValidExpression(jobInfo.getScheduleConf())) {
				return ReturnT.instance(ReturnT.FAIL_CODE, "Cron" + I18nUtil.getString("system_unvalid"));
			}
		}
		// 或者是 scheduleTypeEnum == ScheduleTypeEnum.FIX_DELAY
		else if (scheduleTypeEnum == ScheduleTypeEnum.FIX_RATE) {
			if (jobInfo.getScheduleConf() == null) {
				return ReturnT.instance(ReturnT.FAIL_CODE,
						(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
			}
			try {
				int fixSecond = Integer.parseInt(jobInfo.getScheduleConf());
				if (fixSecond < 1) {
					return ReturnT.instance(ReturnT.FAIL_CODE,
							(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
				}
			}
			catch (Exception e) {
				return ReturnT.instance(ReturnT.FAIL_CODE,
						(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
			}
		}

		// valid advanced
		if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_executorRouteStrategy") + I18nUtil.getString("system_unvalid")));
		}
		if (MisfireStrategyEnum.match(jobInfo.getMisfireStrategy(), null) == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("misfire_strategy") + I18nUtil.getString("system_unvalid")));
		}
		if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_executorBlockStrategy") + I18nUtil.getString("system_unvalid")));
		}

		// 》ChildJobId valid
		if (jobInfo.getChildJobId() != null && !jobInfo.getChildJobId().trim().isEmpty()) {
			String[] childJobIds = jobInfo.getChildJobId().split(",");
			for (String childJobIdItem : childJobIds) {
				if (childJobIdItem != null && !childJobIdItem.trim().isEmpty() && isNumeric(childJobIdItem)) {
					XxlJobInfo childJobInfo = xxlJobInfoDao.loadById(Integer.parseInt(childJobIdItem));
					if (childJobInfo == null) {
						return ReturnT.instance(ReturnT.FAIL_CODE,
								MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})"
										+ I18nUtil.getString("system_not_found")), childJobIdItem));
					}
				}
				else {
					return ReturnT.instance(ReturnT.FAIL_CODE,
							MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})"
									+ I18nUtil.getString("system_unvalid")), childJobIdItem));
				}
			}

			// join , avoid "xxx,,"
			StringBuilder temp = new StringBuilder();
			for (String item : childJobIds) {
				temp.append(item).append(",");
			}
			temp = new StringBuilder(temp.substring(0, temp.length() - 1));

			jobInfo.setChildJobId(temp.toString());
		}

		// group valid
		XxlJobGroup jobGroup = xxlJobGroupDao.load(jobInfo.getJobGroup());
		if (jobGroup == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_jobgroup") + I18nUtil.getString("system_unvalid")));
		}

		// stage job info
		XxlJobInfo exists_jobInfo = xxlJobInfoDao.loadById(jobInfo.getId());
		if (exists_jobInfo == null) {
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("jobinfo_field_id") + I18nUtil.getString("system_not_found")));
		}

		// next trigger time (5s后生效，避开预读周期)
		long nextTriggerTime = exists_jobInfo.getTriggerNextTime();
		boolean scheduleDataNotChanged = jobInfo.getScheduleType().equals(exists_jobInfo.getScheduleType())
				&& jobInfo.getScheduleConf().equals(exists_jobInfo.getScheduleConf());
		if (exists_jobInfo.getTriggerStatus() == 1 && !scheduleDataNotChanged) {
			try {
				Date nextValidTime = JobScheduleHelper.generateNextValidTime(jobInfo,
						new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
				if (nextValidTime == null) {
					return ReturnT.instance(ReturnT.FAIL_CODE,
							(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
				}
				nextTriggerTime = nextValidTime.getTime();
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				return ReturnT.instance(ReturnT.FAIL_CODE,
						(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
			}
		}

		exists_jobInfo.setJobGroup(jobInfo.getJobGroup());
		exists_jobInfo.setJobDesc(jobInfo.getJobDesc());
		exists_jobInfo.setAuthor(jobInfo.getAuthor());
		exists_jobInfo.setAlarmEmail(jobInfo.getAlarmEmail());
		exists_jobInfo.setScheduleType(jobInfo.getScheduleType());
		exists_jobInfo.setScheduleConf(jobInfo.getScheduleConf());
		exists_jobInfo.setMisfireStrategy(jobInfo.getMisfireStrategy());
		exists_jobInfo.setExecutorRouteStrategy(jobInfo.getExecutorRouteStrategy());
		exists_jobInfo.setExecutorHandler(jobInfo.getExecutorHandler());
		exists_jobInfo.setExecutorParam(jobInfo.getExecutorParam());
		exists_jobInfo.setExecutorBlockStrategy(jobInfo.getExecutorBlockStrategy());
		exists_jobInfo.setExecutorTimeout(jobInfo.getExecutorTimeout());
		exists_jobInfo.setExecutorFailRetryCount(jobInfo.getExecutorFailRetryCount());
		exists_jobInfo.setChildJobId(jobInfo.getChildJobId());
		exists_jobInfo.setTriggerNextTime(nextTriggerTime);

		exists_jobInfo.setUpdateTime(new Date());
		xxlJobInfoDao.update(exists_jobInfo);

		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> remove(int id) {
		XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(id);
		if (xxlJobInfo == null) {
			return ReturnT.SUCCESS;
		}

		xxlJobInfoDao.delete(id);
		xxlJobLogDao.delete(id);
		xxlJobLogGlueDao.deleteByJobId(id);
		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> start(int id) {
		XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(id);

		// valid
		ScheduleTypeEnum scheduleTypeEnum = ScheduleTypeEnum.match(xxlJobInfo.getScheduleType(), ScheduleTypeEnum.NONE);
		if (ScheduleTypeEnum.NONE == scheduleTypeEnum) {
			return ReturnT.instance(ReturnT.FAIL_CODE, (I18nUtil.getString("schedule_type_none_limit_start")));
		}

		// next trigger time (5s后生效，避开预读周期)
		long nextTriggerTime = 0;
		try {
			Date nextValidTime = JobScheduleHelper.generateNextValidTime(xxlJobInfo,
					new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
			if (nextValidTime == null) {
				return ReturnT.instance(ReturnT.FAIL_CODE,
						(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
			}
			nextTriggerTime = nextValidTime.getTime();
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ReturnT.instance(ReturnT.FAIL_CODE,
					(I18nUtil.getString("schedule_type") + I18nUtil.getString("system_unvalid")));
		}

		xxlJobInfo.setTriggerStatus(1);
		xxlJobInfo.setTriggerLastTime(0L);
		xxlJobInfo.setTriggerNextTime(nextTriggerTime);

		xxlJobInfo.setUpdateTime(new Date());
		xxlJobInfoDao.update(xxlJobInfo);
		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> stop(int id) {
		XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(id);

		xxlJobInfo.setTriggerStatus(0);
		xxlJobInfo.setTriggerLastTime(0L);
		xxlJobInfo.setTriggerNextTime(0L);

		xxlJobInfo.setUpdateTime(new Date());
		xxlJobInfoDao.update(xxlJobInfo);
		return ReturnT.SUCCESS;
	}

	@Override
	public ReturnT<String> trigger(XxlJobUser loginUser, int jobId, String executorParam, String addressList) {
		// permission
		// if (loginUser == null) {
		// return ReturnT.instance(ReturnT.FAIL.getCode(),
		// I18nUtil.getString("system_permission_limit"));
		// }
		XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(jobId);
		if (xxlJobInfo == null) {
			return ReturnT.instance(ReturnT.FAIL.getCode(), I18nUtil.getString("jobinfo_glue_jobid_unvalid"));
		}
		if (!hasPermission(loginUser, xxlJobInfo.getJobGroup())) {
			return ReturnT.instance(ReturnT.FAIL.getCode(), I18nUtil.getString("system_permission_limit"));
		}

		// force cover job param
		if (executorParam == null) {
			executorParam = "";
		}

		JobTriggerPoolHelper.trigger(jobId, TriggerTypeEnum.MANUAL, -1, null, executorParam, addressList);
		return ReturnT.SUCCESS;
	}

	private boolean hasPermission(XxlJobUser loginUser, int jobGroup) {

		// 跳过鉴权
		loginUser = Optional.ofNullable(loginUser).orElseGet(() -> {
			XxlJobUser xxlJobUser = new XxlJobUser();
			xxlJobUser.setRole(1);
			return xxlJobUser;
		});

		if (loginUser.getRole() == 1) {
			return true;
		}
		List<String> groupIdStrs = new ArrayList<>();
		if (loginUser.getPermission() != null && !loginUser.getPermission().trim().isEmpty()) {
			groupIdStrs = Arrays.asList(loginUser.getPermission().trim().split(","));
		}
		return groupIdStrs.contains(String.valueOf(jobGroup));
	}

	@Override
	public Map<String, Object> dashboardInfo() {

		int jobInfoCount = xxlJobInfoDao.findAllCount();
		int jobLogCount = 0;
		int jobLogSuccessCount = 0;
		XxlJobLogReport xxlJobLogReport = xxlJobLogReportDao.queryLogReportTotal();
		if (xxlJobLogReport != null) {
			jobLogCount = xxlJobLogReport.getRunningCount() + xxlJobLogReport.getSucCount()
					+ xxlJobLogReport.getFailCount();
			jobLogSuccessCount = xxlJobLogReport.getSucCount();
		}

		// executor count
		Set<String> executorAddressSet = new HashSet<>();
		List<XxlJobGroup> groupList = xxlJobGroupDao.findAll();

		if (groupList != null && !groupList.isEmpty()) {
			for (XxlJobGroup group : groupList) {
				if (group.getRegistryList() != null && !group.getRegistryList().isEmpty()) {
					executorAddressSet.addAll(group.getRegistryList());
				}
			}
		}

		int executorCount = executorAddressSet.size();

		Map<String, Object> dashboardMap = new HashMap<>();
		dashboardMap.put("jobInfoCount", jobInfoCount);
		dashboardMap.put("jobLogCount", jobLogCount);
		dashboardMap.put("jobLogSuccessCount", jobLogSuccessCount);
		dashboardMap.put("executorCount", executorCount);
		return dashboardMap;
	}

	@Override
	public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate) {

		// process
		List<String> triggerDayList = new ArrayList<>();
		List<Integer> triggerDayCountRunningList = new ArrayList<>();
		List<Integer> triggerDayCountSucList = new ArrayList<>();
		List<Integer> triggerDayCountFailList = new ArrayList<>();
		int triggerCountRunningTotal = 0;
		int triggerCountSucTotal = 0;
		int triggerCountFailTotal = 0;

		List<XxlJobLogReport> logReportList = xxlJobLogReportDao.queryLogReport(startDate, endDate);

		if (logReportList != null && !logReportList.isEmpty()) {
			for (XxlJobLogReport item : logReportList) {
				String day = DateUtil.formatDate(item.getTriggerDay());
				int triggerDayCountRunning = item.getRunningCount();
				int triggerDayCountSuc = item.getSucCount();
				int triggerDayCountFail = item.getFailCount();

				triggerDayList.add(day);
				triggerDayCountRunningList.add(triggerDayCountRunning);
				triggerDayCountSucList.add(triggerDayCountSuc);
				triggerDayCountFailList.add(triggerDayCountFail);

				triggerCountRunningTotal += triggerDayCountRunning;
				triggerCountSucTotal += triggerDayCountSuc;
				triggerCountFailTotal += triggerDayCountFail;
			}
		}
		else {
			for (int i = -6; i <= 0; i++) {
				triggerDayList.add(DateUtil.formatDate(DateUtil.addDays(new Date(), i)));
				triggerDayCountRunningList.add(0);
				triggerDayCountSucList.add(0);
				triggerDayCountFailList.add(0);
			}
		}

		Map<String, Object> result = new HashMap<>();
		result.put("triggerDayList", triggerDayList);
		result.put("triggerDayCountRunningList", triggerDayCountRunningList);
		result.put("triggerDayCountSucList", triggerDayCountSucList);
		result.put("triggerDayCountFailList", triggerDayCountFailList);

		result.put("triggerCountRunningTotal", triggerCountRunningTotal);
		result.put("triggerCountSucTotal", triggerCountSucTotal);
		result.put("triggerCountFailTotal", triggerCountFailTotal);

		return ReturnT.instance(result);
	}

}
