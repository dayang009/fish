package com.xxl.job.admin.dao;

import com.fish.common.core.entity.XxlJobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * job info
 *
 * @author xuxueli
 * @since 2016-1-12
 */
@Mapper
public interface XxlJobInfoDao {

	List<XxlJobInfo> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize,
			@Param("jobGroup") int jobGroup, @Param("triggerStatus") int triggerStatus,
			@Param("jobDesc") String jobDesc, @Param("executorHandler") String executorHandler,
			@Param("author") String author);

	int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize, @Param("jobGroup") int jobGroup,
			@Param("triggerStatus") int triggerStatus, @Param("jobDesc") String jobDesc,
			@Param("executorHandler") String executorHandler, @Param("author") String author);

	int save(XxlJobInfo info);

	XxlJobInfo loadById(@Param("id") Serializable id);

	int update(XxlJobInfo xxlJobInfo);

	int delete(@Param("id") Serializable id);

	List<XxlJobInfo> getJobsByGroup(@Param("jobGroup") Serializable jobGroup);

	int findAllCount();

	/**
	 * 预读查询任务
	 * @param maxNextTime 当前时间 + 预读时间
	 * @param pagesize 一次读多少条
	 * @return 执行任务的集合
	 */
	List<XxlJobInfo> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize);

	int scheduleUpdate(XxlJobInfo xxlJobInfo);

}
