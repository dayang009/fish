package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * @author xuxueli 2020-04-11 22:27
 */
public class IdleBeatParam implements Serializable {

	private static final long serialVersionUID = -271009846575557609L;

	private Integer jobId;

	public IdleBeatParam() {
	}

	public IdleBeatParam(Integer jobId) {
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
		return "IdleBeatParam{" + "jobId=" + jobId + '}';
	}

}
