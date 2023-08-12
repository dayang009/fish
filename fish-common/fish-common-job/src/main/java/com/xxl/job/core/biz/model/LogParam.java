package com.xxl.job.core.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2020-04-11 22:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogParam implements Serializable {

	private static final long serialVersionUID = -9107734770505631255L;

	private Long logDateTim;

	private Long logId;

	private Integer fromLineNum;

}
