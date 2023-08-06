package com.xxl.job.admin.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * xxl-job log, used to track trigger process
 *
 * @author xuxueli
 * @date 2015-12-19 23:19:09
 */
public class XxlJobLog implements Serializable {

	private static final long serialVersionUID = -3464165513675494721L;

	private long id;

	/**
	 * job info
	 */
	private int jobGroup;

	private int jobId;

	/**
	 * execute info
	 */
	private String executorAddress;

	private String executorHandler;

	private String executorParam;

	private String executorShardingParam;

	private int executorFailRetryCount;

	/**
	 * trigger info
	 */
	private Date triggerTime;

	private int triggerCode;

	private String triggerMsg;

	/**
	 * handle info
	 */
	private Date handleTime;

	private int handleCode;

	private String handleMsg;

	/**
	 * alarm info
	 */
	private int alarmStatus;

	public XxlJobLog() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(int jobGroup) {
		this.jobGroup = jobGroup;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getExecutorAddress() {
		return executorAddress;
	}

	public void setExecutorAddress(String executorAddress) {
		this.executorAddress = executorAddress;
	}

	public String getExecutorHandler() {
		return executorHandler;
	}

	public void setExecutorHandler(String executorHandler) {
		this.executorHandler = executorHandler;
	}

	public String getExecutorParam() {
		return executorParam;
	}

	public void setExecutorParam(String executorParam) {
		this.executorParam = executorParam;
	}

	public String getExecutorShardingParam() {
		return executorShardingParam;
	}

	public void setExecutorShardingParam(String executorShardingParam) {
		this.executorShardingParam = executorShardingParam;
	}

	public int getExecutorFailRetryCount() {
		return executorFailRetryCount;
	}

	public void setExecutorFailRetryCount(int executorFailRetryCount) {
		this.executorFailRetryCount = executorFailRetryCount;
	}

	public Date getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}

	public int getTriggerCode() {
		return triggerCode;
	}

	public void setTriggerCode(int triggerCode) {
		this.triggerCode = triggerCode;
	}

	public String getTriggerMsg() {
		return triggerMsg;
	}

	public void setTriggerMsg(String triggerMsg) {
		this.triggerMsg = triggerMsg;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public int getHandleCode() {
		return handleCode;
	}

	public void setHandleCode(int handleCode) {
		this.handleCode = handleCode;
	}

	public String getHandleMsg() {
		return handleMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	public int getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(int alarmStatus) {
		this.alarmStatus = alarmStatus;
	}

	@Override
	public String toString() {
		return "XxlJobLog{" + "id=" + id + ", jobGroup=" + jobGroup + ", jobId=" + jobId + ", executorAddress='"
				+ executorAddress + '\'' + ", executorHandler='" + executorHandler + '\'' + ", executorParam='"
				+ executorParam + '\'' + ", executorShardingParam='" + executorShardingParam + '\''
				+ ", executorFailRetryCount=" + executorFailRetryCount + ", triggerTime=" + triggerTime
				+ ", triggerCode=" + triggerCode + ", triggerMsg='" + triggerMsg + '\'' + ", handleTime=" + handleTime
				+ ", handleCode=" + handleCode + ", handleMsg='" + handleMsg + '\'' + ", alarmStatus=" + alarmStatus
				+ '}';
	}

}
