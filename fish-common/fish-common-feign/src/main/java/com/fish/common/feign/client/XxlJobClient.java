package com.fish.common.feign.client;

import com.fish.common.core.entity.XxlJobInfo;
import com.fish.common.core.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "fish-job-admin", path = "/xxl-job-admin/jobinfo")
public interface XxlJobClient {

	@GetMapping("/add")
	@ResponseBody
	RespResult<String> add(XxlJobInfo jobInfo);

}
