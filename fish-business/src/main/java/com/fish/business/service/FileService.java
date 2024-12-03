package com.fish.business.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fish.business.mvnentity.SettingsRoot;
import com.google.gson.Gson;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;

/**
 * @author dayang
 */
@Slf4j
@Service
public class FileService {

	@Resource
	private Gson gson;

	public void handleXmlFile(@RequestParam File file) {
		String s1 = FileUtil.readUtf8String(file);
		String s = analysisFile(file);
		JSONObject entries = JSONUtil.parseFromXml(s1);
		JSONObject setString = entries.getJSONObject("settings");

		SettingsRoot bean = setString.toBean(SettingsRoot.class);
		System.out.println("bean = " + bean);

	}

	public String analysisFile(@RequestParam File file) {

		// 创建saxReader对象
		SAXReader reader = SAXReader.createDefault();
		Document document;
		try {
			document = reader.read(file);
		}
		catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
		XmlMapper xmlMapper = new XmlMapper(jacksonXmlModule);

		Element rootElement = document.getRootElement();

		List<Element> servers = rootElement.elements("servers");
		Document demoDoc = this.getDemoDoc();
		for (Element server : servers) {
			demoDoc.getRootElement().setParent(null);
			server.add(demoDoc.getRootElement());
		}

		return rootElement.asXML();

	}

	private Document getDemoDoc() {
		String demoXmlStr = "<server><id>dayangTest</id><username>admin</username><password>qwer!@#$</password></server>";
		try {
			return DocumentHelper.parseText(demoXmlStr);
		}
		catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

}
