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

	public FishCloudException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public FishCloudException(ResponseEnum statusCode) {
		super(statusCode.getMsg());
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
	}

	public FishCloudException(ResponseEnum statusCode, String msg) {
		super(msg);
		this.code = statusCode.getCode();
		this.msg = statusCode.getMsg();
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
