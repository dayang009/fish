package com.fish.file.monitor;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目启动之后开启文件监听功能
 *
 * @author dayang
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "aaa")
public class DirtyRecordRunner implements CommandLineRunner {

	public String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(this.getClass().getName() + " ==> 开启文件夹监听功能");
		TempFileListener tempFileListener = SpringUtil.getBean(TempFileListener.class);
		FileMonitor fileMonitor = new FileMonitor();
		fileMonitor.monitor(filePath, tempFileListener);
		fileMonitor.start();
	}

}
