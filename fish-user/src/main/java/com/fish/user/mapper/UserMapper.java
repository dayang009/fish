package com.fish.user.mapper;

import com.fish.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dayang
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2023-07-13 13:38:54
 * @Entity generator.entity.User
 */
@Mapper
public interface UserMapper {

	/**
	 * 插入一条记录
	 * @param user 实体对象
	 * @return 影响数据库行数
	 */
	int insert(User user);

	/**
	 * 插入数据集合
	 * @param entityList
	 * @return
	 */
	int insertBatchSomeColumn(List<User> entityList);

	int delete(User user);

	/**
	 * 生产环境请使用标记删除
	 * @param id 要删除数据的主键 id
	 * @return 删除的数据行数
	 */
	int deleteById(@Param("id") Serializable id);

	int deleteByMap(Map<String, Object> columnMap);

	int updateById(User user);

	User selectById(@Param("id") Serializable id);

	List<User> selectByIds(List<? extends Serializable> idList);

	/**
	 * 根据 entity 条件，查询一条记录
	 * @param user
	 * @return
	 */
	User selectOne(User user);

	List<User> selectBatchIds(List<? extends Serializable> idList);

}
