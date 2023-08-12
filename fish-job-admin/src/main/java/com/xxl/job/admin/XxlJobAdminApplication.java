package com.xxl.job.admin;

import com.xxl.job.core.autocfg.XxlAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuxueli
 * @since 2018-10-28
 */
@SpringBootApplication(exclude = { XxlAutoConfig.class })
public class XxlJobAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(XxlJobAdminApplication.class, args);
	}

}
