package com.fish.business;

import com.fish.common.feign.client.JobInfoClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { BusinessApplication.class })
@Slf4j
public class XxlJobTest {

	@Resource
	private JobInfoClient jobInfoClient;

	@Test
	public void addTaskTest() {

	}

}
