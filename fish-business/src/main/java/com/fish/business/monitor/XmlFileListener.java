package com.fish.business.monitor;

import com.fish.business.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.io.File;

/**
 * 自定义文件监听类并继承 {@link FileAlterationListenerAdaptor} 实现对文件与目录的创建，修改，删除事件的处理
 *
 * @author dayang
 */
@Component
@Slf4j
public class XmlFileListener extends FileAlterationListenerAdaptor {

	@Resource
	private FileService fileService;

	@Override
	public void onDirectoryChange(File directory) {
		log.info("目录改变 ===> {}", directory.getAbsolutePath());
	}

	@Override
	public void onDirectoryCreate(File directory) {
		log.info("目录新建 ===> {}", directory.getAbsolutePath());
	}

	@Override
	public void onDirectoryDelete(File directory) {
		log.info("目录删除 ===> {}", directory.getAbsolutePath());
	}

	@Override
	public void onFileChange(File file) {
		log.info("文件改变 ===> {}", file.getAbsolutePath());
	}

	@Override
	public void onFileCreate(File file) {
		log.info("文件创建 ===> {}", file.getAbsolutePath());
		fileService.handleXmlFile(file);
	}

	@Override
	public void onFileDelete(File file) {
		log.info("文件删除 ===> {}", file.getAbsolutePath());
	}

}
