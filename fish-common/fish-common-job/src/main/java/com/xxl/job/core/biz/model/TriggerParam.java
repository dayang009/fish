package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2016/7/22
 */
public class TriggerParam implements Serializable {

	private static final long serialVersionUID = -7243289487115662159L;

	private Integer jobId;

	private String executorHandler;

	private String executorParams;

	private String executorBlockStrategy;

	private Integer executorTimeout;

	private Long logId;

	private Long logDateTime;

	private String glueType;

	private String glueSource;

	private Long glueUpdatetime;

	private Integer broadcastIndex;

	private Integer broadcastTotal;

	public TriggerParam() {
	}

	public TriggerParam(Integer jobId, String executorHandler, String executorParams, String executorBlockStrategy,
			Integer executorTimeout, Long logId, Long logDateTime, String glueType, String glueSource,
			Long glueUpdatetime, Integer broadcastIndex, Integer broadcastTotal) {
		this.jobId = jobId;
		this.executorHandler = executorHandler;
		this.executorParams = executorParams;
		this.executorBlockStrategy = executorBlockStrategy;
		this.executorTimeout = executorTimeout;
		this.logId = logId;
		this.logDateTime = logDateTime;
		this.glueType = glueType;
		this.glueSource = glueSource;
		this.glueUpdatetime = glueUpdatetime;
		this.broadcastIndex = broadcastIndex;
		this.broadcastTotal = broadcastTotal;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getExecutorHandler() {
		return executorHandler;
	}

	public void setExecutorHandler(String executorHandler) {
		this.executorHandler = executorHandler;
	}

	public String getExecutorParams() {
		return executorParams;
	}

	public void setExecutorParams(String executorParams) {
		this.executorParams = executorParams;
	}

	public String getExecutorBlockStrategy() {
		return executorBlockStrategy;
	}

	public void setExecutorBlockStrategy(String executorBlockStrategy) {
		this.executorBlockStrategy = executorBlockStrategy;
	}

	public Integer getExecutorTimeout() {
		return executorTimeout;
	}

	public void setExecutorTimeout(Integer executorTimeout) {
		this.executorTimeout = executorTimeout;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(Long logDateTime) {
		this.logDateTime = logDateTime;
	}

	public String getGlueType() {
		return glueType;
	}

	public void setGlueType(String glueType) {
		this.glueType = glueType;
	}

	public String getGlueSource() {
		return glueSource;
	}

	public void setGlueSource(String glueSource) {
		this.glueSource = glueSource;
	}

	public Long getGlueUpdatetime() {
		return glueUpdatetime;
	}

	public void setGlueUpdatetime(Long glueUpdatetime) {
		this.glueUpdatetime = glueUpdatetime;
	}

	public Integer getBroadcastIndex() {
		return broadcastIndex;
	}

	public void setBroadcastIndex(Integer broadcastIndex) {
		this.broadcastIndex = broadcastIndex;
	}

	public Integer getBroadcastTotal() {
		return broadcastTotal;
	}

	public void setBroadcastTotal(Integer broadcastTotal) {
		this.broadcastTotal = broadcastTotal;
	}

	@Override
	public String toString() {
		return "TriggerParam{" + "jobId=" + jobId + ", executorHandler='" + executorHandler + '\''
				+ ", executorParams='" + executorParams + '\'' + ", executorBlockStrategy='" + executorBlockStrategy
				+ '\'' + ", executorTimeout=" + executorTimeout + ", logId=" + logId + ", logDateTime=" + logDateTime
				+ ", glueType='" + glueType + '\'' + ", glueSource='" + glueSource + '\'' + ", glueUpdatetime="
				+ glueUpdatetime + ", broadcastIndex=" + broadcastIndex + ", broadcastTotal=" + broadcastTotal + '}';
	}

}
