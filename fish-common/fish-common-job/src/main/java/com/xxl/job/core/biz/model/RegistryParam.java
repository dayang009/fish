package com.xxl.job.core.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xuxueli
 * @date 2017-05-10 20:22:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistryParam implements Serializable {

	private static final long serialVersionUID = -7330337708651380242L;

	private String registryGroup;

	private String registryKey;

	private String registryValue;

}
