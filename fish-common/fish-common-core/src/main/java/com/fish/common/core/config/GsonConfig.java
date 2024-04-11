package com.fish.common.core.config;

import cn.hutool.core.date.DatePattern;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class GsonConfig {

	/**
	 * <a href=
	 * "https://springdoc.cn/spring-boot/application-properties.html#appendix.application-properties.json">完整配置</a>
	 */
	@Bean
	public GsonBuilder gsonBuilder(List<GsonBuilderCustomizer> customizers) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		// 将Gson配置为序列化空字段。默认情况下，Gson会省略序列化过程中为null的所有字段
		gsonBuilder.serializeNulls();
		// 默认情况下，Gson转义HTML字符，如＜＞等。使用此选项可将Gson配置为按原样传递HTML字符。
		gsonBuilder.disableHtmlEscaping();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
		customizers.forEach(gsonBuilderCustomizer -> gsonBuilderCustomizer.customize(gsonBuilder));
		gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
		return gsonBuilder;
	}

}
