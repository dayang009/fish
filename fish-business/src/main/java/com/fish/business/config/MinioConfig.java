package com.fish.business.config;

import io.minio.MinioClient;
import javax.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 核心配置类
 *
 * @author dayang
 */
@Configuration
@EnableConfigurationProperties(MinioProp.class)
public class MinioConfig {

	@Resource
	private MinioProp minioProp;

	/**
	 * 获取 MinioClient
	 */
	@Bean
	public MinioClient getMinioClient() {
		return MinioClient.builder()
			.endpoint(minioProp.getEndpoint())
			.credentials(minioProp.getAccessKey(), minioProp.getSecretKey())
			.build();
	}

}
