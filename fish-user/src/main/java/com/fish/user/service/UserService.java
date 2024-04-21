package com.fish.user.service;

import com.fish.common.core.exception.FishCloudException;
import com.fish.common.core.util.ResponseEnum;
import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
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
public class UserService {

	@Resource
	private UserMapper userMapper;

	public String login(String account, String password) {
		User user = new User();
		user.setUserAccount(account);
		user.setUserPassword(password);

		User user1 = userMapper.selectOne(user);
		if (user1 != null) {
			return "Login Success";
		}
		else {
			return "Login Failed";
		}
	}

	public String register(User user) {
		if (this.checkUserExist(user.getUserAccount())) {
			throw new FishCloudException(ResponseEnum.VALIDATE_ERROR, "用户已经存在");
		}
		userMapper.insert(user);
		return "Register Success";

	}

	public boolean checkUserExist(String account) {
		User user = new User();
		user.setUserAccount(account);
		User user1 = userMapper.selectOne(user);
		return user1 != null;
	}

	public boolean save(User user) {
		return false;
	}

	public boolean saveBatch(Collection<User> entityList) {
		return false;
	}

	public boolean saveBatch(Collection<User> entityList, int batchSize) {
		return false;
	}

	public boolean saveOrUpdate(User entity) {
		return false;
	}

	public boolean saveOrUpdate(User entity, Map updateWrapper) {
		return false;
	}

	public boolean saveOrUpdateBatch(Collection<User> entityList) {
		return false;
	}

	public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
		return false;
	}

	public boolean remove(User queryWrapper) {
		return false;
	}

	public boolean removeById(Serializable id) {
		return false;
	}

	public boolean removeByMap(Map<String, Object> columnMap) {
		return false;
	}

	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return false;
	}

	public boolean updateById(User entity) {
		return false;
	}

	public boolean updateBatchById(Collection<User> entityList) {
		return false;
	}

	public boolean updateBatchById(Collection<User> entityList, int batchSize) {
		return false;
	}

	public User getById(Serializable id) {
		return null;
	}

	public User getOne(Map<String, Object> map) {
		return null;
	}

	public User getOne(Map<String, Object> map, boolean throwEx) {
		return null;
	}

	public List<User> list() {
		return null;
	}

	public List<User> list(Map map) {
		return null;
	}

	public Collection<User> listByIds(Collection<? extends Serializable> idList) {
		return null;
	}

	public Collection<User> listByMap(Map<String, Object> columnMap) {
		return null;
	}

	public List<Map<String, Object>> listMaps() {
		return null;
	}

	public List<Map<String, Object>> listMaps(Map<String, Object> queryWrapper) {
		return null;
	}

	public List<Object> listObjs() {
		return null;
	}

	public <V> List<V> listObjs(Function<? super Object, V> mapper) {
		return null;
	}

}
