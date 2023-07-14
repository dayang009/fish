package com.fish.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 为模块配置允许跨域
 * <p>
 * SpringBoot小于2.4版本使用 .allowedOrigins("*")
 * <p>
 * SpringBoot大于2.4版本使用 .allowedOriginPatterns("*")
 *
 * @author dayang
 * @since SpringBoot 2.4
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping("/**")
			// 允许跨域请求的域名，低版本SpringBoot使用这个属性allowedOrigins("*")
			.allowedOriginPatterns("*")
			// 是否允许Cookie
			.allowCredentials(true)
			// 允许的请求方式，"*"表示允许所有的请求方式
			.allowedMethods("GET", "POST", "DELETE", "PUT")
			// 允许的Header属性
			.allowedHeaders("*")
			// 跨域允许的时间
			.maxAge(60 * 60 * 24);
	}

}
