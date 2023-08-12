package com.fish.common.mybatis.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dayang
 * @since 2023/8/11
 */
public interface FishMapper<T> extends Mapper<T> {

	/**
	 * 插入一条记录
	 * @param bean 实体对象
	 * @return 影响数据库行数
	 */
	int insert(T bean);

	int insertBatchSomeColumn(List<T> entityList);

	int delete(T T);

	/**
	 * 生产环境请使用标记删除
	 * @param id 要删除数据的主键 id
	 * @return 删除的数据行数
	 */
	int deleteById(Serializable id);

	int deleteByMap(Map<String, Object> columnMap);

	int updateById(T T);

	T selectById(Serializable id);

	List<T> selectByIds(List<? extends Serializable> idList);

	/**
	 * 根据 entity 条件，查询一条记录
	 * @param bean
	 * @return
	 */
	T selectOne(T bean);

	List<T> selectBatchIds(List<? extends Serializable> idList);

	List<T> findAll();

}
