package com.fish.common.core.util;

import java.io.Serializable;

/**
 * common return
 *
 * @param <T>
 * @author xuxueli
 * @since 2015-12-4 16:32:31
 */
public class ReturnT<T> implements Serializable {

	private static final long serialVersionUID = -2149301035770816301L;

	public static final int SUCCESS_CODE = 200;

	public static final int FAIL_CODE = 500;

	public static final ReturnT<String> SUCCESS = ReturnT.instance(null);

	public static final ReturnT<String> FAIL = ReturnT.instance(FAIL_CODE, null);

	private int code;

	private String msg;

	private T content;

	public ReturnT() {
	}

	public ReturnT(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ReturnT(T content) {
		this.code = SUCCESS_CODE;
		this.content = content;
	}

	public static <T> ReturnT<T> instance(int code, String msg) {
		return new ReturnT<>(code, msg);
	}

	public static <T> ReturnT<T> instance(T content) {
		return new ReturnT<>(content);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ReturnT [code=" + code + ", msg=" + msg + ", content=" + content + "]";
	}

}
