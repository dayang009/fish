package com.fish.flow;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		// 完成流程的部署操作
		Deployment deploy = repositoryService.createDeployment()
			.addClasspathResource("holiday-request.bpmn20.xml")
			.name("请假流程")
			.deploy();

		System.out.println("deploy.getId() = " + deploy.getId());
		System.out.println("deploy.getName() = " + deploy.getName());
	}

	@Test
	void findDeployMsg() {
		ProcessEngine processEngine = cfg.buildProcessEngine();

		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
			.deploymentId("1")
			.singleResult();

		System.out.println("processDefinition.getDeploymentId() = " + processDefinition.getDeploymentId());
		System.out.println("processDefinition.getName() = " + processDefinition.getName());
		System.out.println("processDefinition.getDescription() = " + processDefinition.getDescription());
		System.out.println("processDefinition.getId() = " + processDefinition.getId());
	}

	@Test
	void deleteDeploy() {
		ProcessEngine processEngine = cfg.buildProcessEngine();

		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.deleteDeployment("1", true);
	}

	@Test
	void runProcess() {
		ProcessEngine processEngine = cfg.buildProcessEngine();
		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<>();
		variables.put("employee", "张三");
		variables.put("nrOfHolidays", 5);
		variables.put("desciption", "累了");

		ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
		System.out.println("holidayRequest.getProcessDefinitionId() = " + holidayRequest.getProcessDefinitionId());
		System.out.println("holidayRequest.getActivityId() = " + holidayRequest.getActivityId());
		System.out.println("holidayRequest.getId() = " + holidayRequest.getId());
	}

	/**
	 * 查询任务
	 */
	@Test
	void queryTask() {
		ProcessEngine processEngine = cfg.buildProcessEngine();
		TaskService taskService = processEngine.getTaskService();
		List<Task> list = taskService.createTaskQuery()
			.processDefinitionKey("holidayRequest")
			.taskAssignee("zhangSan")
			.list();
		for (Task task : list) {
			System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
			System.out.println("task.getName() = " + task.getName());
			System.out.println("task.getAssignee() = " + task.getAssignee());
			System.out.println("task.getId() = " + task.getId());
		}
	}

	@Test
	void completeTask() {
		ProcessEngine processEngine = cfg.buildProcessEngine();
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery()
			.processDefinitionKey("holidayRequest")
			.taskAssignee("zhangSan")
			.singleResult();

		Map<String, Object> map = new HashMap<>();
		map.put("approved", false);
		taskService.complete(task.getId(), map);
	}

	@Test
	void getHistory() {
		ProcessEngine processEngine = cfg.buildProcessEngine();
		HistoryService historyService = processEngine.getHistoryService();
		List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
			.processDefinitionId("holidayRequest:1:3")
			.finished()
			.orderByHistoricActivityInstanceEndTime()
			.asc()
			.list();

		for (HistoricActivityInstance historicActivityInstance : list) {
			System.out
				.println("historicActivityInstance.getActivityName() = " + historicActivityInstance.getActivityName());
			System.out.println("historicActivityInstance.getDurationInMillis() = "
					+ historicActivityInstance.getDurationInMillis());

		}
	}

}
