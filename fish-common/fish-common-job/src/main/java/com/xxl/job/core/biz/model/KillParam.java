package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * @author xuxueli 2020-04-11 22:27
 */
public class KillParam implements Serializable {

	private static final long serialVersionUID = 6865886753419565394L;

	private Integer jobId;

	public KillParam() {
	}

	public KillParam(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	@Override
	public String toString() {
		return "KillParam{" + "jobId=" + jobId + '}';
	}

}
