package com.xxl.job.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * xxl-job log for glue, used to track job code process
 *
 * @author xuxueli 2016-5-19 17:57:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XxlJobLogGlue implements Serializable {

	private static final long serialVersionUID = -5218976610810450584L;

	private Integer id;

	/**
	 * 任务主键ID
	 */
	private Integer jobId;

	/**
	 * GLUE类型 {@link com.xxl.job.core.glue.GlueTypeEnum}
	 */
	private String glueType;

	private String glueSource;

	private String glueRemark;

	private Date addTime;

	private Date updateTime;

}
