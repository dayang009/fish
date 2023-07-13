package com.fish.user.service.impl;

import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
import com.fish.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author dayang
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-07-13 13:38:54
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public boolean save(User user) {
		return false;
	}

	@Override
	public boolean saveBatch(Collection<User> entityList) {
		return false;
	}

	@Override
	public boolean saveBatch(Collection<User> entityList, int batchSize) {
		return false;
	}

	@Override
	public boolean saveOrUpdate(User entity) {
		return false;
	}

	@Override
	public boolean saveOrUpdate(User entity, Map updateWrapper) {
		return false;
	}

	@Override
	public boolean saveOrUpdateBatch(Collection<User> entityList) {
		return false;
	}

	@Override
	public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
		return false;
	}

	@Override
	public boolean remove(User queryWrapper) {
		return false;
	}

	@Override
	public boolean removeById(Serializable id) {
		return false;
	}

	@Override
	public boolean removeByMap(Map<String, Object> columnMap) {
		return false;
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return false;
	}

	@Override
	public boolean updateById(User entity) {
		return false;
	}

	@Override
	public boolean updateBatchById(Collection<User> entityList) {
		return false;
	}

	@Override
	public boolean updateBatchById(Collection<User> entityList, int batchSize) {
		return false;
	}

	@Override
	public User getById(Serializable id) {
		return null;
	}

	@Override
	public User getOne(Map<String, Object> map) {
		return null;
	}

	@Override
	public User getOne(Map<String, Object> map, boolean throwEx) {
		return null;
	}

	@Override
	public List<User> list() {
		return null;
	}

	@Override
	public List<User> list(Map map) {
		return null;
	}

	@Override
	public Collection<User> listByIds(Collection<? extends Serializable> idList) {
		return null;
	}

	@Override
	public Collection<User> listByMap(Map<String, Object> columnMap) {
		return null;
	}

	@Override
	public List<Map<String, Object>> listMaps() {
		return null;
	}

	@Override
	public List<Map<String, Object>> listMaps(Map<String, Object> queryWrapper) {
		return null;
	}

	@Override
	public List<Object> listObjs() {
		return null;
	}

	@Override
	public <V> List<V> listObjs(Function<? super Object, V> mapper) {
		return null;
	}

}
