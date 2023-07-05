package com.fish.file.service.impl;

import com.fish.file.entity.FileBody;
import com.fish.file.entity.FileHead;
import com.fish.file.entity.SatelliteFile;
import com.fish.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author dayang
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

	@Override
	public void handleXmlFile(@RequestParam File file) {
		SatelliteFile satelliteFile = analysisFile(file);
		System.out.println(satelliteFile);
	}

	@Override
	public SatelliteFile analysisFile(@RequestParam File file) {

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

		return satelliteFile;
	}

}
