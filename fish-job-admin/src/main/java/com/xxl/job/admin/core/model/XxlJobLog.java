package com.xxl.job.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * xxl-job log, used to track trigger process
 *
 * @author xuxueli 2015-12-19 23:19:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XxlJobLog implements Serializable {

	private static final long serialVersionUID = -3464165513675494721L;

	private Long id;

	/**
	 * job info
	 */
	private Integer jobGroup;

	private Integer jobId;

	/**
	 * execute info
	 */
	private String executorAddress;

	private String executorHandler;

	private String executorParam;

	private String executorShardingParam;

	private Integer executorFailRetryCount;

	/**
	 * trigger info
	 */
	private Date triggerTime;

	private Integer triggerCode;

	private String triggerMsg;

	/**
	 * handle info
	 */
	private Date handleTime;

	private Integer handleCode;

	private String handleMsg;

	/**
	 * alarm info
	 */
	private Integer alarmStatus;

}
