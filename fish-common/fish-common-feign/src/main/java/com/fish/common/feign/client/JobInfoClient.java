package com.fish.common.feign.client;

import com.fish.common.core.entity.XxlJobInfo;
import com.fish.common.core.util.ReturnT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * xxl-job 远程调用 todo 对方模块接收到的实体类值为空
 */
@FeignClient(value = "fish-job-admin", path = "/xxl-job-admin/jobinfo")
public interface JobInfoClient {

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	ReturnT<String> add(XxlJobInfo jobInfo);

}
