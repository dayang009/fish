package com.fish.business;

import com.fish.common.feign.client.JobInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = { BusinessApplication.class })
@Slf4j
public class XxlJobTest {

	@Resource
	private JobInfoClient jobInfoClient;

	@Test
	public void addTaskTest() {

	}

}
