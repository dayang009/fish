package com.fish.common.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xuxueli
 * @since 2016/9/30
 */
public class XxlJobRegistry implements Serializable {

	private static final long serialVersionUID = -1200550184540495577L;

	private int id;

	private String registryGroup;

	private String registryKey;

	private String registryValue;

	private Date updateTime;

	public XxlJobRegistry() {
	}

	public XxlJobRegistry(int id, String registryGroup, String registryKey, String registryValue, Date updateTime) {
		this.id = id;
		this.registryGroup = registryGroup;
		this.registryKey = registryKey;
		this.registryValue = registryValue;
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistryGroup() {
		return registryGroup;
	}

	public void setRegistryGroup(String registryGroup) {
		this.registryGroup = registryGroup;
	}

	public String getRegistryKey() {
		return registryKey;
	}

	public void setRegistryKey(String registryKey) {
		this.registryKey = registryKey;
	}

	public String getRegistryValue() {
		return registryValue;
	}

	public void setRegistryValue(String registryValue) {
		this.registryValue = registryValue;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "XxlJobRegistry{" + "id=" + id + ", registryGroup='" + registryGroup + '\'' + ", registryKey='"
				+ registryKey + '\'' + ", registryValue='" + registryValue + '\'' + ", updateTime=" + updateTime + '}';
	}

}
