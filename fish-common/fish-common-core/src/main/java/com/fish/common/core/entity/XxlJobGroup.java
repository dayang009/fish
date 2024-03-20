package com.fish.common.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xuxueli
 * @since 2016/9/30
 */
public class XxlJobGroup implements Serializable {

	private static final long serialVersionUID = 6349474431468224317L;

	private int id;

	private String appname;

	private String title;

	/**
	 * 执行器地址类型：0=自动注册、1=手动录入
	 */
	private int addressType = 0;

	/**
	 * 执行器地址列表，多地址逗号分隔(手动录入)
	 */
	private String addressList;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
	private Date updateTime;

	/**
	 * registry list 执行器地址列表(系统注册)
	 */
	private List<String> registryList;

	/**
	 * 获取注册列表
	 * @return 集合
	 */
	public List<String> getRegistryList() {
		if (addressList != null && !addressList.trim().isEmpty()) {
			registryList = new ArrayList<>(Arrays.asList(addressList.split(",")));
		}
		return registryList;
	}

	public XxlJobGroup() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAddressType() {
		return addressType;
	}

	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}

	public String getAddressList() {
		return addressList;
	}

	public void setAddressList(String addressList) {
		this.addressList = addressList;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setRegistryList(List<String> registryList) {
		this.registryList = registryList;
	}

}
