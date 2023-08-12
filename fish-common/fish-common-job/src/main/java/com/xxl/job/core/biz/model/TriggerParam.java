package com.xxl.job.core.biz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xuxueli
 * @since 2016/7/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TriggerParam implements Serializable {

	private static final long serialVersionUID = -7243289487115662159L;

	private Integer jobId;

	private String executorHandler;

	private String executorParams;

	private String executorBlockStrategy;

	private Integer executorTimeout;

	private Long logId;

	private Long logDateTime;

	private String glueType;

	private String glueSource;

	private Long glueUpdatetime;

	private Integer broadcastIndex;

	private Integer broadcastTotal;

}
