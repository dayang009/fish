package com.xxl.job.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XxlJobLogReport implements Serializable {

	private static final long serialVersionUID = -2011619318421655140L;

	private Integer id;

	private Date triggerDay;

	private Integer runningCount;

	private Integer sucCount;

	private Integer failCount;

}
