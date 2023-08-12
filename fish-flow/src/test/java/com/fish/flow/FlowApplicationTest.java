package com.fish.flow;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author dayang
 */
class FlowApplicationTest {

	ProcessEngineConfiguration cfg = null;

	@BeforeEach
	void beforeRun() {
		cfg = new StandaloneProcessEngineConfiguration();
		cfg.setJdbcDriver("com.mysql.cj.jdbc.Driver");
		cfg.setJdbcUsername("root");
		cfg.setJdbcPassword("root");
		cfg.setJdbcUrl(
				"jdbc:mysql://127.0.0.1:3306/flowable?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
		cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
	}

	@Test
	void deployTest() {

		ProcessEngine processEngine = cfg.buildProcessEngine();

		RepositoryService repositoryService = processEngine.getRepositoryService();

		Deployment deploy = repositoryService.createDeployment()
			.addClasspathResource("holiday-request.bpmn20.xml")
			.name("请假流程")
			.deploy();

		System.out.println("deploy.getId() = " + deploy.getId());
		System.out.println("deploy.getName() = " + deploy.getName());
	}

}
