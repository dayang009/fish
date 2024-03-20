package com.fish.common.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * xxl-job log for glue, used to track job code process
 *
 * @author xuxueli
 * @since 2016-5-19 17:57:46
 */
public class XxlJobLogGlue implements Serializable {

	private static final long serialVersionUID = -5218976610810450584L;

	private int id;

	/**
	 * 任务主键ID
	 */
	private int jobId;

	/**
	 * GLUE类型 {@link com.xxl.job.core.glue.GlueTypeEnum}
	 */
	private String glueType;

	private String glueSource;

	private String glueRemark;

	private Date addTime;

	private Date updateTime;

	public XxlJobLogGlue() {
	}

	public XxlJobLogGlue(int id, int jobId, String glueType, String glueSource, String glueRemark, Date addTime,
			Date updateTime) {
		this.id = id;
		this.jobId = jobId;
		this.glueType = glueType;
		this.glueSource = glueSource;
		this.glueRemark = glueRemark;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
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

	public String getGlueRemark() {
		return glueRemark;
	}

	public void setGlueRemark(String glueRemark) {
		this.glueRemark = glueRemark;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "XxlJobLogGlue{" + "id=" + id + ", jobId=" + jobId + ", glueType='" + glueType + '\'' + ", glueSource='"
				+ glueSource + '\'' + ", glueRemark='" + glueRemark + '\'' + ", addTime=" + addTime + ", updateTime="
				+ updateTime + '}';
	}

}
