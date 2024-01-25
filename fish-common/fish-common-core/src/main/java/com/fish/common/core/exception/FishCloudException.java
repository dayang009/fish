package com.fish.common.core.exception;

import com.fish.common.core.util.IResult;
import com.fish.common.core.util.ResponseEnum;

/**
 * @author dayang
 */
public class FishCloudException extends RuntimeException implements IResult {

	private static final long serialVersionUID = 3483395350561004351L;

	private final String code;

	private final String msg;

	private final Object data;

	public FishCloudException(String code, String msg, Object data) {
		super(msg);
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public FishCloudException(ResponseEnum statusCode, String desc) {
		super(statusCode.getMsg());
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
		this.data = desc;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

}
