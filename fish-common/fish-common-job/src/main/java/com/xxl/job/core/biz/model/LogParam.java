package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2020-04-11 22:27
 */
public class LogParam implements Serializable {

	private static final long serialVersionUID = -9107734770505631255L;

	private Long logDateTim;

	private Long logId;

	private Integer fromLineNum;

	public LogParam() {
	}

	public LogParam(Long logDateTim, Long logId, Integer fromLineNum) {
		this.logDateTim = logDateTim;
		this.logId = logId;
		this.fromLineNum = fromLineNum;
	}

	public Long getLogDateTim() {
		return logDateTim;
	}

	public void setLogDateTim(Long logDateTim) {
		this.logDateTim = logDateTim;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Integer getFromLineNum() {
		return fromLineNum;
	}

	public void setFromLineNum(Integer fromLineNum) {
		this.fromLineNum = fromLineNum;
	}

	@Override
	public String toString() {
		return "LogParam{" + "logDateTim=" + logDateTim + ", logId=" + logId + ", fromLineNum=" + fromLineNum + '}';
	}

}
