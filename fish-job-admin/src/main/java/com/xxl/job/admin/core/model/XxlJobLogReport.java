package com.xxl.job.admin.core.model;

import java.io.Serializable;
import java.util.Date;

public class XxlJobLogReport implements Serializable {

	private static final long serialVersionUID = -2011619318421655140L;

	private int id;

	private Date triggerDay;

	private int runningCount;

	private int sucCount;

	/**
	 * 失败次数
	 */
	private int failCount;

	public XxlJobLogReport() {
	}

	public XxlJobLogReport(int id, Date triggerDay, int runningCount, int sucCount, int failCount) {
		this.id = id;
		this.triggerDay = triggerDay;
		this.runningCount = runningCount;
		this.sucCount = sucCount;
		this.failCount = failCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTriggerDay() {
		return triggerDay;
	}

	public void setTriggerDay(Date triggerDay) {
		this.triggerDay = triggerDay;
	}

	public int getRunningCount() {
		return runningCount;
	}

	public void setRunningCount(int runningCount) {
		this.runningCount = runningCount;
	}

	public int getSucCount() {
		return sucCount;
	}

	public void setSucCount(int sucCount) {
		this.sucCount = sucCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	@Override
	public String toString() {
		return "XxlJobLogReport{" + "id=" + id + ", triggerDay=" + triggerDay + ", runningCount=" + runningCount
				+ ", sucCount=" + sucCount + ", failCount=" + failCount + '}';
	}

}
