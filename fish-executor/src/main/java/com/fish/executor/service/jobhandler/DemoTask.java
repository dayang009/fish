package com.fish.executor.service.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoTask {

	@XxlJob(value = "test")
	public void demo01() {
		String jobParam = XxlJobHelper.getJobParam();
		log.info(jobParam);

	}

}
