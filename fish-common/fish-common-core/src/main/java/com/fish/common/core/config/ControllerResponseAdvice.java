package com.fish.common.core.config;

import com.fish.common.core.util.BaseResponse;
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
		if (returnType.getParameterType().isAssignableFrom(BaseResponse.class)) {
			isAdvice = false;
		}
		// 添加不需要包装的注解也不需要再次包装了
		else if (returnType.hasMethodAnnotation(NotControllerResponseAdvice.class)) {
			isAdvice = false;
		}

		return isAdvice;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		return new BaseResponse<>(body);
	}

}
