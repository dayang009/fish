package com.xxl.job.core.autocfg;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author dayang
 */
@EnableConfigurationProperties(value = { XxlConfigProperty.class })
public class XxlAutoConfig {

	private final Logger logger = LoggerFactory.getLogger(XxlAutoConfig.class);

	@Resource
	private XxlConfigProperty xxlConfigProperty;

	@Bean
	public XxlJobSpringExecutor xxlJobExecutor() {
		logger.info(">>>>>>>>>>> xxl-job config init.");
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		xxlJobSpringExecutor.setAdminAddresses(xxlConfigProperty.getAdminAddresses());
		xxlJobSpringExecutor.setAppname(xxlConfigProperty.getAppname());
		xxlJobSpringExecutor.setAddress(xxlConfigProperty.getAddress());
		xxlJobSpringExecutor.setIp(xxlConfigProperty.getIp());
		xxlJobSpringExecutor.setPort(xxlConfigProperty.getPort());
		xxlJobSpringExecutor.setAccessToken(xxlConfigProperty.getAccessToken());
		xxlJobSpringExecutor.setLogPath(xxlConfigProperty.getLogPath());
		xxlJobSpringExecutor.setLogRetentionDays(xxlConfigProperty.getLogRetentionDays());

		return xxlJobSpringExecutor;
	}

}
