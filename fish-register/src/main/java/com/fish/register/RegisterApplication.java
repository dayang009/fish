package com.fish.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka 注册中心服务端
 *
 * @author dayang
 * @since 0.1.0
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

}
