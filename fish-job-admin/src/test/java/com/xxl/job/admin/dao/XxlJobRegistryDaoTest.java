package com.xxl.job.admin.dao;

import com.fish.common.core.entity.XxlJobRegistry;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XxlJobRegistryDaoTest {

	@Resource
	private XxlJobRegistryDao xxlJobRegistryDao;

	@Test
	public void test() {
		int ret = xxlJobRegistryDao.registryUpdate("g1", "k1", "v1", new Date());
		if (ret < 1) {
			ret = xxlJobRegistryDao.registrySave("g1", "k1", "v1", new Date());
		}

		List<XxlJobRegistry> list = xxlJobRegistryDao.findAll(1, new Date());

		int ret2 = xxlJobRegistryDao.removeDead(Arrays.asList(1));
	}

}
