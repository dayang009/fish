package com.fish.common.core.util;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author dayang
 */
@Data
@Accessors(chain = true)
public class RespResult<T> implements Serializable {

	private static final long serialVersionUID = 5697795170240018136L;

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 提示信息
	 */
	private String msg;

	/**
	 * 传输的数据
	 */
	private transient T data;

	/**
	 * 空参构造器私有化，禁止外部实例化空对象
	 */
	private RespResult() {
	}

	/**
	 * 全参构造器，手动设置返回 VO
	 * @param code 状态码
	 * @param msg 消息
	 * @param data 数据
	 */
	public RespResult(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 默认返回成功状态码，数据对象
	 * @param data 数据
	 */
	public RespResult(T data) {
		this.code = ResponseEnum.SUCCESS.getCode();
		this.msg = ResponseEnum.SUCCESS.getMsg();
		this.data = data;
	}

	/**
	 * 返回指定状态码，数据对象
	 * @param responseEnum 返回结果枚举类
	 * @param data 数据
	 */
	public RespResult(ResponseEnum responseEnum, T data) {
		this.code = responseEnum.getCode();
		this.msg = responseEnum.getMsg();
		this.data = data;
	}

	/**
	 * 只返回状态码
	 * @param responseEnum 返回结果枚举类
	 */
	public RespResult(ResponseEnum responseEnum) {
		this.code = responseEnum.getCode();
		this.msg = responseEnum.getMsg();
		this.data = null;
	}

	public static <T> RespResult<T> instance(String code, String msg, T data) {
		return new RespResult<T>().setCode(code).setMsg(msg).setData(data);
	}

	public static <T> RespResult<T> success() {
		return new RespResult<T>().setCode(ResponseEnum.SUCCESS.getCode()).setMsg(ResponseEnum.SUCCESS.getMsg());
	}

	public static <T> RespResult<T> success(T data) {
		return new RespResult<T>().setCode(ResponseEnum.SUCCESS.getCode())
			.setMsg(ResponseEnum.SUCCESS.getMsg())
			.setData(data);
	}

	public static <T> RespResult<T> success(String msg, T data) {
		return new RespResult<T>().setCode(ResponseEnum.SUCCESS.getCode()).setMsg(msg).setData(data);
	}

	public static <T> RespResult<T> fail() {
		return new RespResult<T>().setCode(ResponseEnum.FAIL.getCode()).setMsg(ResponseEnum.FAIL.getMsg());
	}

	public static <T> RespResult<T> fail(T data) {
		return new RespResult<T>().setCode(ResponseEnum.FAIL.getCode())
			.setMsg(ResponseEnum.FAIL.getMsg())
			.setData(data);
	}

	public static <T> RespResult<T> fail(ResponseEnum responseEnum, T data) {
		return new RespResult<T>().setCode(responseEnum.getCode()).setMsg(responseEnum.getMsg()).setData(data);
	}

}
