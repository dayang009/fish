package com.fish.user.controller;

import com.fish.common.core.exception.FishCloudException;
import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ResponseEnum;
import com.fish.user.adapter.Login3rdAdapter;
import com.fish.user.entity.User;
import com.fish.user.mapper.UserMapper;
import com.fish.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @author dayang
 */
@Tag(name = "用户接口")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private UserMapper userMapper;

	@Resource
	private Login3rdAdapter login3rdAdapter;

	@GetMapping("/user1")
	public String demo01() {

		User user = new User();
		user.setNickName("zhangSan");
		user.setUserAccount("qwer");
		user.setUserPassword("123456");
		user.setGender(0);
		user.setAge(10);
		user.setPhone("188555588");
		user.setEmail("123@qq.com");
		user.setAdminFlag(0);

		int insert = userMapper.insert(user);
		return String.valueOf(insert);
	}

	@PostMapping("/user")
	public RespResult<String> addUser(@Validated @RequestBody List<@Valid User> users) {
		userMapper.insertBatchSomeColumn(users);
		return RespResult.success("批量数据插入成功");
	}

	@PostMapping("")
	public RespResult<?> save(@RequestBody @Validated User user) {
		userMapper.insert(user);
		return RespResult.success(user);
	}

	@DeleteMapping("/{id}")
	public RespResult<?> delete(@PathVariable Serializable id) {
		userMapper.deleteById(id);
		return RespResult.success("用户删除成功");
	}

	@PutMapping("/")
	public RespResult<?> update(@RequestBody @Validated User user) {
		int i = userMapper.updateById(user);
		if (i == 0) {
			throw new FishCloudException(ResponseEnum.USER_ERROR, "原用户不存在");
		}
		return RespResult.success("用户修改成功");
	}

	@GetMapping("/{id}")
	public RespResult<User> getById(@PathVariable Serializable id) {
		User user = userMapper.selectById(id);
		if (user == null) {
			throw new FishCloudException(ResponseEnum.USER_REQ_PARAS_ERROR, "无法通过此ID查到用户");
		}
		return RespResult.success(user);
	}

	@PostMapping("/login")
	public RespResult<?> login(@RequestBody @Validated User user) {
		String login = userService.login(user.getUserAccount(), user.getUserPassword());
		return RespResult.success(login);
	}

	@PostMapping("/register")
	public RespResult<?> register(@RequestBody @Validated User user) {
		String register = userService.register(user);
		return RespResult.success(register);
	}

	/**
	 * Gitee平台回调接口，携带参数返回
	 */
	@GetMapping("/gitee")
	public RespResult<?> gitee(String code, String state, HttpServletRequest servletRequest) {
		String s = login3rdAdapter.loginByGitee(code, state);
		return RespResult.success(s);
	}

}
