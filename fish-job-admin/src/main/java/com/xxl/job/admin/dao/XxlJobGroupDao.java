package com.xxl.job.admin.dao;

import com.fish.common.core.entity.XxlJobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xuxueli
 * @since 2016/9/30
 */
@Mapper
public interface XxlJobGroupDao {

	List<XxlJobGroup> findAll();

	List<XxlJobGroup> findByAddressType(@Param("addressType") int addressType);

	int save(XxlJobGroup xxlJobGroup);

	int update(XxlJobGroup xxlJobGroup);

	int remove(@Param("id") int id);

	XxlJobGroup load(@Param("id") int id);

	List<XxlJobGroup> pageList(@Param("offset") int offset, @Param("pagesize") int pagesize,
			@Param("appname") String appname, @Param("title") String title);

	int pageListCount(@Param("offset") int offset, @Param("pagesize") int pagesize, @Param("appname") String appname,
			@Param("title") String title);

}
