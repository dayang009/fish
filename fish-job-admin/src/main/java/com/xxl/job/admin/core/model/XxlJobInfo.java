package com.xxl.job.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * xxl-job info
 *
 * @author xuxueli
 * @date 2016-1-12 18:25:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XxlJobInfo implements Serializable {

	private static final long serialVersionUID = 6828320007570127498L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 执行器主键ID
	 */
	private Integer jobGroup;

	private String jobDesc;

	private Date addTime;

	private Date updateTime;

	/**
	 * 负责人
	 */
	private String author;

	/**
	 * 报警邮件
	 */
	private String alarmEmail;

	/**
	 * 调度类型
	 */
	private String scheduleType;

	/**
	 * 调度配置，值含义取决于调度类型
	 */
	private String scheduleConf;

	/**
	 * 调度过期策略
	 */
	private String misfireStrategy;

	/**
	 * 执行器路由策略
	 */
	private String executorRouteStrategy;

	/**
	 * 执行器，任务Handler名称
	 */
	private String executorHandler;

	/**
	 * 执行器，任务参数
	 */
	private String executorParam;

	/**
	 * 阻塞处理策略
	 */
	private String executorBlockStrategy;

	/**
	 * 任务执行超时时间，单位秒
	 */
	private Integer executorTimeout;

	/**
	 * 失败重试次数
	 */
	private Integer executorFailRetryCount;

	/**
	 * GLUE类型 {@link com.xxl.job.core.glue.GlueTypeEnum}
	 */
	private String glueType;

	/**
	 * GLUE源代码
	 */
	private String glueSource;

	/**
	 * GLUE备注
	 */
	private String glueRemark;

	/**
	 * GLUE更新时间
	 */
	private Date glueUpdatetime;

	/**
	 * 子任务ID，多个逗号分隔
	 */
	private String childJobId;

	/**
	 * 调度状态：0-停止，1-运行
	 */
	private Integer triggerStatus;

	/**
	 * 上次调度时间
	 */
	private Long triggerLastTime;

	/**
	 * 下次调度时间
	 */
	private Long triggerNextTime;

}
