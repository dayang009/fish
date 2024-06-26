package com.xxl.job.admin.core.trigger;

import com.xxl.job.admin.core.util.I18nUtil;

/**
 * trigger type enum
 *
 * @author xuxueli
 * @since 2018-09-16 04:56:41
 */
public enum TriggerTypeEnum {

	// @formatter:off
	MANUAL(I18nUtil.getString("jobconf_trigger_type_manual")),
	CRON(I18nUtil.getString("jobconf_trigger_type_cron")),
	RETRY(I18nUtil.getString("jobconf_trigger_type_retry")),
	PARENT(I18nUtil.getString("jobconf_trigger_type_parent")),
	API(I18nUtil.getString("jobconf_trigger_type_api")),
	MISFIRE(I18nUtil.getString("jobconf_trigger_type_misfire")),
	;
	// @formatter:on

	TriggerTypeEnum(String title) {
		this.title = title;
	}

	private final String title;

	public String getTitle() {
		return title;
	}

}
