package com.fish.common.core.exception;

import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 全局异常处理器
 *
 * @author dayang
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(FishCloudException.class)
	public RespResult<?> fishExceptionHandler(FishCloudException e) {
		log.error("FishException:" + e.getMessage(), e);
		return RespResult.fail(e.getCode(), e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public RespResult<?> runtimeExceptionHandler(RuntimeException e) {
		log.error("runtimeException", e);
		return RespResult.fail(ResponseEnum.SYSTEM_ERROR.getCode(), e.getMessage());
	}

	@ExceptionHandler(BindException.class)
	public RespResult<?> validException(BindException e) {
		log.error("BindException", e);
		return RespResult.fail(ResponseEnum.SYSTEM_ERROR.getCode(),
				Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
	}

}
