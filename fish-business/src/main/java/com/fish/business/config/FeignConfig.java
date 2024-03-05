package com.fish.business.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(value = { "com.fish.common.feign.client" })
@Configuration
public class FeignConfig {

}
