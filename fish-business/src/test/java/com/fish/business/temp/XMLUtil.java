package com.fish.business.temp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * 将XML转换成Object
 */
public class XMLUtil {

	/**
	 * 将String类型的xml转换成对象
	 */
	public static Object convertXmlStrToObject(Class<?> clazz, String xmlStr) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			// 进行将Xml转成对象的核心接口
			Unmarshaller unmarshal = context.createUnmarshaller();
			StringReader sr = new StringReader(xmlStr);
			xmlObject = unmarshal.unmarshal(sr);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

	/**
	 * 将file类型的xml转换成对象
	 */
	public static Object convertXmlFileToObject(Class<?> clazz, String xmlPath) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStreamReader isr = new InputStreamReader(new FileInputStream(xmlPath), "UTF-8");
			xmlObject = unmarshaller.unmarshal(isr);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

}
