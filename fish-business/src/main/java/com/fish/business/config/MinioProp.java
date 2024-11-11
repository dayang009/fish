package com.fish.business.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * Minio配置类
 *
 * @author dayang
 */
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProp implements Serializable {

	private static final long serialVersionUID = -1371137311556124097L;

	/**
	 * 链接 URL
	 */
	private String endpoint;

	/**
	 * 用户名称
	 */
	private String accessKey;

	/**
	 * 密码
	 */
	private String secretKey;

	public MinioProp() {
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	@Override
	public String toString() {
		return "MinioConfig{" + "endpoint='" + endpoint + '\'' + ", accessKey='" + accessKey + '\'' + ", secretKey='"
				+ secretKey + '\'' + '}';
	}

}
