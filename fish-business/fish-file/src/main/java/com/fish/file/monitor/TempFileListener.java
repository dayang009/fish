package com.fish.file.monitor;

import com.fish.file.entity.FileBody;
import com.fish.file.entity.FileHead;
import com.fish.file.entity.SatelliteFile;
import com.fish.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		// 创建saxReader对象
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(file);
		}
		catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		Element rootElement = document.getRootElement();
		Element fileHeader = rootElement.element("fileHead");
		Element fileBodies = rootElement.element("fileBody");

		FileHead fileHead = new FileHead();
		fileHead.setName(fileHeader.elementTextTrim("name"));
		fileHead.setAge(Integer.parseInt(fileHeader.elementTextTrim("age")));

		FileBody fileBody = new FileBody();
		String createTime = fileBodies.elementTextTrim("createTime");

		fileBody.setCreateTime(LocalDateTime.parse(createTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		SatelliteFile satelliteFile = new SatelliteFile(fileHead, fileBody);

		System.out.println(satelliteFile);
	}

	@Override
	public void onFileDelete(File file) {
		System.out.println("文件删除" + file.getAbsolutePath());
	}

}
