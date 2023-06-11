package com.fish.common.core.util;

/**
 * 状态码接口
 *
 * @author dayang
 */
public interface IResult {

	/**
	 * 获取状态码
	 * @return 状态码
	 */
	String getCode();

	/**
	 * 返回到前端的信息
	 * @return 提示信息
	 */
	String getMsg();

}
