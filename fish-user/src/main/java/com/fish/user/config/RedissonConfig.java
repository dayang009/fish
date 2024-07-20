package com.fish.user.config;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * Redisson 配置
 */
@Configuration
public class RedissonConfig {

	@Resource
	private RedisProperties redisProperties;

	@Bean
	public RedissonClient redisson() {
		// 1. Create config object
		Config config = new Config();
		config.setCodec(new StringCodec(StandardCharsets.UTF_8));
		config.useSingleServer()
			.setAddress(String.format("redis://%s:%d", redisProperties.getHost(), redisProperties.getPort()))
			.setDatabase(redisProperties.getDatabase())
			.setPassword(StrUtil.isNotEmpty(redisProperties.getPassword()) ? redisProperties.getPassword() : null);
		// 2. Create Redisson instance
		return Redisson.create(config);
	}

}
