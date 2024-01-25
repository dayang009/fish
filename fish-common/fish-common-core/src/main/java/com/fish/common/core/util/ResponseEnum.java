package com.fish.common.core.util;

import lombok.Getter;

/**
 * 状态码枚举类
 *
 * @author dayang
 */
@Getter
public enum ResponseEnum implements IResult {

	// @formatter:off
	/**
	 * 错误产生来源分为 A/B/C，
	 * A 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付超时等问题；
	 * B 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题；
	 * C 表示错误来源于第三方服务，比如 CDN 服务出错，消息投递超时等问题；
	 * 四位数字编号从 0001 到 9999，大类之间的步长间距预留 100
	 */
    SUCCESS("00000", "一切OK"),
    USER_ERROR("A0001", "用户端错误"),
    USER_REQ_PARAS_ERROR("A0400", "用户请求参数错误"),
	USER_NULL_PARAS_ERROR("A0500", "用户请求的参数有空值"),


    SYSTEM_RUN_ERROR("B0001", "系统执行出错"),
    SYSTEM_TIME_OUT("B0100", "系统执行超时"),
    SYSTEM_RESOURCE_ERROR("B0300", "系统资源异常"),

    CALL_SERVICE_ERROR("C0001", "调用第三方服务出错"),
    MIDDLEWARE_SERVICE_ERROR("C0100", "中间件服务出错"),
    RPC_SERVICE_ERROR("C0110", "RPC 服务出错"),
    OTHER_TIME_OUT("C0200", "第三方系统执行超时"),
    DB_SERVICE_ERROR("C0300", "数据库服务出错"),



    FAIL("600", "fail"),
    VALIDATE_ERROR("1002", "参数校验失败"),
    RESPONSE_PACK_ERROR("1003", "response返回包装失败"),
    APP_ERROR("2000", "业务异常"),
    PRICE_ERROR("2001", "价格异常"),
    SYSTEM_ERROR("5000", "系统错误，请稍后再试"),
    DATE_TIME_PARSE_ERROR("5001", "日期时间解析异常"),
	TOKEN_PARSE_ERROR("5002", "Token 解析异常"),
    ;
    // @formatter:on

	private final String code;

	private final String msg;

	ResponseEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
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
