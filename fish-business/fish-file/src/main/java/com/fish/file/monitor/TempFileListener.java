package com.fish.file.monitor;

import com.fish.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * 自定义文件监听类并继承 FileAlterationListenerAdaptor 实现对文件与目录的创建，修改，删除事件的处理
 *
 * @author dayang
 */
@Component
@Slf4j
public class TempFileListener extends FileAlterationListenerAdaptor {

	@Resource
	private FileService fileService;

	@Override
	public void onDirectoryChange(File directory) {
		System.out.println("目录改变：" + directory.getAbsolutePath());
	}

	@Override
	public void onDirectoryCreate(File directory) {
		System.out.println("目录新建：" + directory.getAbsolutePath());
	}

	@Override
	public void onDirectoryDelete(File directory) {
		System.out.println("目录删除：" + directory.getAbsolutePath());
	}

	@Override
	public void onFileChange(File file) {
		System.out.println("文件改变" + file.getAbsolutePath());
	}

	@Override
	public void onFileCreate(File file) {
		System.out.println("文件创建" + file.getAbsolutePath());
		fileService.handleXmlFile(file);
	}

	@Override
	public void onFileDelete(File file) {
		System.out.println("文件删除" + file.getAbsolutePath());
	}

}
