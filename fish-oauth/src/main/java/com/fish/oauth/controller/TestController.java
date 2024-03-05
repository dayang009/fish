package com.fish.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class TestController {

	@GetMapping("/")
	public Map<String, String> test() {
		return Collections.singletonMap("name", "zhangSan");

	}

}
