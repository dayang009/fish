package com.fish.business;

import com.fish.common.feign.client.XxlJobClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = { BusinessApplication.class })
@Slf4j
public class XxlJobTest {

	@Resource
	private XxlJobClient xxlJobClient;

	@Test
	public void addTaskTest() {

	}

}
