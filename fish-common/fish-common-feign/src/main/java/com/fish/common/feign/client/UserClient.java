package com.fish.common.feign.client;

import com.fish.common.core.entity.Student;
import com.fish.common.core.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(value = "fish-user", path = "/users")
public interface UserClient {

	@GetMapping("/user1")
	String demo01();

	@PostMapping("/user")
	RespResult<String> addUser(@Validated @RequestBody List<@Valid Student> users);

}
