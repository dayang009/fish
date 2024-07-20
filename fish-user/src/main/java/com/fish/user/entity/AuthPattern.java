package com.fish.user.entity;

import java.io.Serializable;

public class AuthPattern implements Serializable {

	/**
	 * 应用唯一标识
	 */
	public static final String CLIENT_ID = "client_id";

	/**
	 * 请使用 urlEncode对链接进行处理
	 */
	public static final String REDIRECT_URI = "redirect_uri";

	public static final String RESPONSE_TYPE = "response_type";

	public static final String SCOPE = "scope";

	public static final String STATE = "state";

}
