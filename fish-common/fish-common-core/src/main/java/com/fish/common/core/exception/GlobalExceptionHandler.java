package com.fish.common.core.exception;

import com.fish.common.core.util.RespResult;
import com.fish.common.core.util.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
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

	@ExceptionHandler(AccessDeniedException.class)
	public RespResult<?> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
		log.error("请求地址 >>> {}, 权限校验失败 >>> {} ", request.getRequestURI(), e.getCause().getMessage());
		return RespResult.fail(ResponseEnum.NOT_AUTH, "操作没有被授权");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public RespResult<?> handHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
			HttpServletRequest request) {
		log.error("请求地址 >>> {}, 不支持 {} 请求", request.getRequestURI(), e.getMethod());
		return RespResult.fail(ResponseEnum.USER_ERROR, "不支持的请求类型");
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

	@ExceptionHandler(BadSqlGrammarException.class)
	public RespResult<?> sqlGrammarException(BadSqlGrammarException e) {
		log.error("SQL 执行异常 >>> ", e.getCause());
		return RespResult.fail(ResponseEnum.BAD_SQL_GRAMMAR, "SQL 执行异常，请联系管理员");
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public RespResult<?> dataIntegrityViolationException(DataIntegrityViolationException e) {
		log.error("SQL 执行异常 >>> ", e.getCause());
		return RespResult.fail(ResponseEnum.BAD_SQL_GRAMMAR, "数据完整性违规异常");
	}

	@ExceptionHandler(BindException.class)
	public RespResult<?> validException(BindException e) {
		log.error("绑定异常 >>> ", e);
		return RespResult.fail(ResponseEnum.SYSTEM_ERROR,
				Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public RespResult<?> runtimeExceptionHandler(RuntimeException e, HttpServletRequest request) {
		log.error("发生未知运行异常，地址：{} >>> ", request.getRequestURI(), e);
		return RespResult.fail(ResponseEnum.SYSTEM_ERROR, "发生未知运行异常");
	}

	@ExceptionHandler(Exception.class)
	public RespResult<?> exceptionHandler(Exception e, HttpServletRequest request) {
		log.error("系统异常，请求地址：{} >>> ", request.getRequestURI(), e);
		return RespResult.fail(ResponseEnum.SYSTEM_ERROR, "系统运行异常，请联系管理员");
	}

}
