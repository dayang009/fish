package com.fish.common.core.exception;

import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
