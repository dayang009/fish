package com.fish.common.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients({ "com.fish.common.feign.client" })
public class FeignConfig {

}
