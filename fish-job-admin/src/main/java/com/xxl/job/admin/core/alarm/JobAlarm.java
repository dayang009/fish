package com.xxl.job.admin.core.alarm;

import com.fish.common.core.entity.XxlJobInfo;
import com.fish.common.core.entity.XxlJobLog;

/**
 * @author xuxueli
 * @since 2020-01-19
 */
public interface JobAlarm {

	/**
	 * job alarm
	 * @param info
	 * @param jobLog
	 * @return
	 */
	boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
