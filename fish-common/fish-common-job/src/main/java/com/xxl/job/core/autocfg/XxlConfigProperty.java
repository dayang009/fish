package com.xxl.job.core.autocfg;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dayang
 */
@ConfigurationProperties(prefix = "xxljob")
public class XxlConfigProperty {

	/**
	 * 注册地址
	 */
	private String adminAddresses = "http://127.0.0.1:8083/xxl-job-admin";

	/**
	 * 通信使用的Token
	 */
	private String accessToken = "default_token";

	/**
	 * 执行器名称
	 */
	private String appname = "fish-executor";

	private String address;

	private String ip;

	private int port = 9999;

	/**
	 * 日志路径
	 */
	private String logPath = "./logs/xxl-job/jobhandler";

	/**
	 * 调度日志默认只保留 30 天
	 */
	private int logRetentionDays = 30;

	public XxlConfigProperty() {
	}

	public String getAdminAddresses() {
		return adminAddresses;
	}

	public void setAdminAddresses(String adminAddresses) {
		this.adminAddresses = adminAddresses;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public int getLogRetentionDays() {
		return logRetentionDays;
	}

	public void setLogRetentionDays(int logRetentionDays) {
		this.logRetentionDays = logRetentionDays;
	}

	@Override
	public String toString() {
		return "XxlConfigProperty{" + "adminAddresses='" + adminAddresses + '\'' + ", accessToken='" + accessToken
				+ '\'' + ", appname='" + appname + '\'' + ", address='" + address + '\'' + ", ip='" + ip + '\''
				+ ", port=" + port + ", logPath='" + logPath + '\'' + ", logRetentionDays=" + logRetentionDays + '}';
	}

}
