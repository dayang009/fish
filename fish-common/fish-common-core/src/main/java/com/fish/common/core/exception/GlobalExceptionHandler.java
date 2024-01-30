package com.fish.common.core.exception;

import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	/**
	 * 自定义业务异常拦截器
	 * @param e 自定义异常
	 * @return 前端包装类
	 */
	@ExceptionHandler(FishCloudException.class)
	public RespResult<?> fishExceptionHandler(FishCloudException e) {
		log.error("自定义业务异常 >>> ", e);
		return RespResult.instance(e.getCode(), e.getMsg(), e.getData());
	}

	@ExceptionHandler(IllegalStateException.class)
	public RespResult<?> illegalStateException(IllegalStateException e) {
		log.error("非法状态异常 >>> ", e);
		return RespResult.fail(ResponseEnum.USER_REQ_PARAS_ERROR, Objects.requireNonNull(e.getMessage()));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public RespResult<?> httpMessageNotReadableException(NestedRuntimeException e) {

		log.error("嵌套运行时异常 >>> ", e.getCause());
		return RespResult.fail(ResponseEnum.VALIDATE_ERROR,
				Objects.requireNonNull(e.getCause().getMessage().split("at \\[Source:")[0]));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RespResult<?> validException(MethodArgumentNotValidException e) {
		log.error("方法参数无效异常 >>> ", e);
		return RespResult.fail(ResponseEnum.USER_REQ_PARAS_ERROR,
				Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ExceptionHandler(BindException.class)
	public RespResult<?> validException(BindException e) {
		log.error("绑定异常 >>> ", e);
		return RespResult.fail(ResponseEnum.SYSTEM_ERROR,
				Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public RespResult<?> runtimeExceptionHandler(RuntimeException e) {
		log.error("运行时异常 >>> ", e);
		return RespResult.fail(ResponseEnum.SYSTEM_ERROR, e.getMessage());
	}

}
