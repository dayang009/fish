package com.xxl.job.admin.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xuxueli
 * @since 2016/9/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XxlJobGroup implements Serializable {

	private static final long serialVersionUID = 6349474431468224317L;

	private Integer id;

	private String appname;

	private String title;

	/**
	 * 执行器地址类型：0=自动注册、1=手动录入
	 */
	private Integer addressType;

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

	public List<String> getRegistryList() {
		if (addressList != null && !addressList.trim().isEmpty()) {
			registryList = new ArrayList<>(Arrays.asList(addressList.split(",")));
		}
		return registryList;
	}

}
