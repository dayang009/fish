package com.fish.common.core.util;

/**
 * @author dayang
 * @since 2023/3/2
 */
public class ApiException extends RuntimeException implements IResult {

	private static final long serialVersionUID = 4495441239591936697L;

	private final String code;

	private final String msg;

	public ApiException(ResponseEnum responseEnum, String msg) {
		super(msg);
		this.code = responseEnum.getCode();
		this.msg = responseEnum.getMsg();
	}

	/**
	 * 默认异常使用 APP_ERROR 状态码
	 * @param msg
	 */
	public ApiException(String msg) {
		super(msg);
		this.code = ResponseEnum.APP_ERROR.getCode();
		this.msg = ResponseEnum.APP_ERROR.getMsg();
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
