package com.fish.user.service;

import com.fish.user.entity.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 针对表【t_user】的数据库操作Service
 *
 * @author dayang
 * @since 2023-07-13 13:38:54
 */
public interface UserService {

	/**
	 * 插入一条记录（选择字段，策略插入）
	 * @param user
	 * @return
	 */
	boolean save(User user);

	/**
	 * 插入（批量）
	 * @param entityList 实体对象集合
	 * @return
	 */
	boolean saveBatch(Collection<User> entityList);

	/**
	 * 插入（批量）
	 * @param entityList 实体对象集合
	 * @param batchSize 插入批次数量
	 * @return
	 */
	boolean saveBatch(Collection<User> entityList, int batchSize);

	/**
	 * TableId 注解存在更新记录，否插入一条记录
	 * @param entity
	 * @return
	 */
	boolean saveOrUpdate(User entity);

	/**
	 * 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
	 * @param entity
	 * @param updateWrapper
	 * @return
	 */
	boolean saveOrUpdate(User entity, Map updateWrapper);

	// 批量修改插入
	boolean saveOrUpdateBatch(Collection<User> entityList);

	// 批量修改插入
	boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize);

	/**
	 * 根据 queryWrapper 设置的条件，删除记录
	 * @param queryWrapper
	 * @return
	 */
	boolean remove(User queryWrapper);

	/**
	 * 根据 ID 删除
	 * @param id
	 * @return
	 */
	boolean removeById(Serializable id);

	// 根据 columnMap 条件，删除记录
	boolean removeByMap(Map<String, Object> columnMap);

	// 删除（根据ID 批量删除）
	boolean removeByIds(Collection<? extends Serializable> idList);

	// 根据 ID 选择修改
	boolean updateById(User entity);

	// 根据ID 批量更新
	boolean updateBatchById(Collection<User> entityList);

	// 根据ID 批量更新
	boolean updateBatchById(Collection<User> entityList, int batchSize);

	/**
	 * 根据 ID 查询
	 * @param id
	 * @return
	 */
	User getById(Serializable id);

	// 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
	User getOne(Map<String, Object> map);

	// 根据 Wrapper，查询一条记录
	User getOne(Map<String, Object> map, boolean throwEx);

	// 查询所有
	List<User> list();

	// 查询列表
	List<User> list(Map map);

	/**
	 * 查询（根据ID 批量查询）
	 * @param idList
	 * @return
	 */
	Collection<User> listByIds(Collection<? extends Serializable> idList);

	/**
	 * 查询（根据 columnMap 条件）
	 * @param columnMap
	 * @return
	 */
	Collection<User> listByMap(Map<String, Object> columnMap);

	/**
	 * 查询所有列表
	 * @return
	 */
	List<Map<String, Object>> listMaps();

	// 查询列表
	List<Map<String, Object>> listMaps(Map<String, Object> queryWrapper);

	// 查询全部记录
	List<Object> listObjs();

	/**
	 * 查询全部记录
	 * @param mapper
	 * @param <V>
	 * @return
	 */
	<V> List<V> listObjs(Function<? super Object, V> mapper);

}
