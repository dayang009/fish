package com.fish.business.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fish.business.mvnentity.SettingsRoot;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * @author dayang
 */
@Slf4j
@Service
public class FileService {

	public void handleXmlFile(@RequestParam File file) {
		String s1 = FileUtil.readUtf8String(file);
		// String s = analysisFile(file);
		JSONObject entries = JSONUtil.parseFromXml(s1);
		JSONObject setString = entries.getJSONObject("settings");

		SettingsRoot bean = setString.toBean(SettingsRoot.class);
		System.out.println("bean = " + bean);

	}

	public String analysisFile(@RequestParam File file) {

		// 创建saxReader对象
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(file);
		}
		catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		System.out.println("document.getDocType() = " + document.getDocType());
		System.out.println("document.getXMLEncoding() = " + document.getXMLEncoding());
		System.out.println("document.getStringValue() = " + document.getStringValue());
		System.out.println("document.getText() = " + document.getText());
		return "hello";

	}

}
