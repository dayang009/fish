package com.fish.common.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fish.common.core.util.RespResult;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author dayang
 */
@RestControllerAdvice(basePackages = { "com.fish" })
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		boolean isAdvice = true;
		// 已经是包装过的类型就不需要再次包装了
		if (returnType.getParameterType().isAssignableFrom(RespResult.class)) {
			isAdvice = false;
		}
		// 添加不需要包装的注解也不需要再次包装了
		else if (returnType.hasMethodAnnotation(NotControllerResponseAdvice.class)) {
			isAdvice = false;
		}

		return isAdvice;
	}

	@SneakyThrows
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof String) {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(RespResult.success(body));
		}
		return RespResult.success(body);
	}

}
