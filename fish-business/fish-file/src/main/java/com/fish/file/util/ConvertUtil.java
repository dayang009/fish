package com.fish.file.util;

import org.apache.commons.lang.CharEncoding;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 转换工具类
 *
 * @author dayang
 */
public class ConvertUtil {

	/**
	 * 将Bean对象转换，生成xml文件
	 * @param object Bean
	 * @param localFilePath 生成文件的路径
	 * @param fileName 生成的文件名
	 * @return 生成文件的全路径
	 * @throws JAXBException bean转换为xml异常
	 * @throws IOException 输出到文件异常
	 */
	public static String beanToXml(Object object, String localFilePath, String fileName)
			throws JAXBException, IOException {
		// 获取JAXB的上下文环境，需要传入具体的 JavaBean
		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		// 创建 Marshaller 实例
		Marshaller marshaller = jaxbContext.createMarshaller();
		// 设置编码格式
		marshaller.setProperty(Marshaller.JAXB_ENCODING, CharEncoding.UTF_8);
		// 设置转换参数，告诉序列化器是否格式化输出
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// 构建输出环境，使用标准输出
		PrintStream out = new PrintStream(Files.newOutputStream(Paths.get(localFilePath + fileName)), true,
				CharEncoding.UTF_8);
		// 将所需对象序列化，该方法没有返回值
		marshaller.marshal(object, out);
		return localFilePath + fileName;
	}

}
