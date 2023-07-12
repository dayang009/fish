package com.fish.register.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dayang
 */
@RestController
public class MyController {

	@GetMapping("/demo01")
	public String demo01() {
		return "Hello";
	}

}
