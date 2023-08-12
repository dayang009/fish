package com.fish.user.mapper;

import com.fish.common.mybatis.mapper.FishMapper;
import com.fish.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dayang
 * @since 2023/8/11
 */
@Mapper
public interface TestMapper extends FishMapper<User> {

	@Override
	int insert(User bean);

	@Override
	int insertBatchSomeColumn(List<User> entityList);

	@Override
	int delete(User T);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(Map<String, Object> columnMap);

	@Override
	int updateById(User T);

	@Override
	User selectById(Serializable id);

	@Override
	List<User> selectByIds(List<? extends Serializable> idList);

	@Override
	User selectOne(User bean);

	@Override
	List<User> selectBatchIds(List<? extends Serializable> idList);

	@Override
	List<User> findAll();

}
