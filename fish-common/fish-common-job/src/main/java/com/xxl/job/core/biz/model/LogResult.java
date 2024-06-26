package com.xxl.job.core.biz.model;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2017/3/23
 */
public class LogResult implements Serializable {

	private static final long serialVersionUID = 8612358856586962197L;

	private Integer fromLineNum;

	private Integer toLineNum;

	private String logContent;

	private Boolean isEnd;

	public LogResult() {
	}

	public LogResult(int fromLineNum, int toLineNum, String logContent, boolean isEnd) {
		this.fromLineNum = fromLineNum;
		this.toLineNum = toLineNum;
		this.logContent = logContent;
		this.isEnd = isEnd;
	}

	public int getFromLineNum() {
		return fromLineNum;
	}

	public void setFromLineNum(int fromLineNum) {
		this.fromLineNum = fromLineNum;
	}

	public int getToLineNum() {
		return toLineNum;
	}

	public void setToLineNum(int toLineNum) {
		this.toLineNum = toLineNum;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean end) {
		isEnd = end;
	}

}
