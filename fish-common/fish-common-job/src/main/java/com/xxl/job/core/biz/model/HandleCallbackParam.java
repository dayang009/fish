package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2017/3/2
 */
public class HandleCallbackParam implements Serializable {

	private static final long serialVersionUID = 7017761588516096351L;

	private Long logId;

	private Long logDateTim;

	private Integer handleCode;

	private String handleMsg;

	public HandleCallbackParam() {
	}

	public HandleCallbackParam(Long logId, Long logDateTim, Integer handleCode, String handleMsg) {
		this.logId = logId;
		this.logDateTim = logDateTim;
		this.handleCode = handleCode;
		this.handleMsg = handleMsg;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getLogDateTim() {
		return logDateTim;
	}

	public void setLogDateTim(Long logDateTim) {
		this.logDateTim = logDateTim;
	}

	public Integer getHandleCode() {
		return handleCode;
	}

	public void setHandleCode(Integer handleCode) {
		this.handleCode = handleCode;
	}

	public String getHandleMsg() {
		return handleMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	@Override
	public String toString() {
		return "HandleCallbackParam{" + "logId=" + logId + ", logDateTim=" + logDateTim + ", handleCode=" + handleCode
				+ ", handleMsg='" + handleMsg + '\'' + '}';
	}

}
