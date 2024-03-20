package com.xxl.job.admin.dao;

import com.fish.common.core.entity.XxlJobLogReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * job log
 *
 * @author xuxueli
 * @since 2019-11-22
 */
@Mapper
public interface XxlJobLogReportDao {

	int save(XxlJobLogReport xxlJobLogReport);

	int update(XxlJobLogReport xxlJobLogReport);

	List<XxlJobLogReport> queryLogReport(@Param("triggerDayFrom") Date triggerDayFrom,
			@Param("triggerDayTo") Date triggerDayTo);

	XxlJobLogReport queryLogReportTotal();

}
