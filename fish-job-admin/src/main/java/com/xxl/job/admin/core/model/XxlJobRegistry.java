package com.xxl.job.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuxueli on 16/9/30.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XxlJobRegistry implements Serializable {

	private static final long serialVersionUID = -1200550184540495577L;

	private Integer id;

	private String registryGroup;

	private String registryKey;

	private String registryValue;

	private Date updateTime;

}
