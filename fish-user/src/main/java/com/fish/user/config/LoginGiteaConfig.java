package com.fish.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "gitea")
public class LoginGiteaConfig {

	/**
	 * 唯一 ID 标识
	 */
	private String clientId;

	/**
	 * 授权密码
	 */
	private String clientSecret;

	/**
	 * Callback 接口地址
	 */
	private String redirectUri;

	private String responseType;

	/**
	 * 请求端随机生成的，接口参数校验作用
	 */
	private String state;

	/**
	 * 通过 code 进行验证
	 */
	private String grantType;

	/**
	 * Gitea 用户登录，自动注册，添加的用户名前缀
	 */
	private String userPrefix;

	private String tokenUrl;

	private String authorizeUrl;

	private String userUrl;

}
