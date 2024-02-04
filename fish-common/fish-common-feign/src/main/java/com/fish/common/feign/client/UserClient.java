package com.fish.common.feign.client;

import com.fish.common.core.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

@FeignClient(value = "fish-user", path = "/users")
public interface UserClient {

	@GetMapping("/user1")
	String demo01();

	@DeleteMapping("/{id}")
	RespResult<?> delete(@PathVariable(value = "id") Serializable id);

}
