package com.fish.common.core;

import com.fish.common.core.exception.FishCloudException;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 业务处理的模板方法
 *
 * @author dayang
 */
@Slf4j
public abstract class ServiceTemplate<T, R> {

	/**
	 * 模板同意暴露执行入口
	 * @param request
	 * @return
	 */
	public R process(T request) {
		// 1.打印入口日志
		log.info("start invoke, request is:{}", request);
		// 开始计时，用于日志记录耗时
		Stopwatch stopwatch = Stopwatch.createStarted();

		try {
			// 2.校验参数
			validParam(request);
			// 3.子类实现逻辑
			R response = doProcess(request);
			// 4.打印出口日志
			long timeCost = stopwatch.elapsed(TimeUnit.MILLISECONDS);
			log.info("end invoke, response is:{} costTime:{}", response, timeCost);
			return response;
		}
		catch (FishCloudException e) {
			// 打印异常日志
			log.error("error invoke, exception:{}", Arrays.toString(e.getStackTrace()));
			return null;
		}
	}

	/**
	 * 参数校验，交给子类实现
	 * @param request
	 */
	protected abstract void validParam(T request);

	/**
	 * 执行业务逻辑，交给子类实现
	 * @param request
	 * @return
	 */
	protected abstract R doProcess(T request);

}
