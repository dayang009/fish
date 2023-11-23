package com.fish.business.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang.CharEncoding;
import org.dom4j.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

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

	/**
	 * 数据转换对象
	 * @param rootElt 要转换的 Element 数据
	 * @param pojo 要转换的目标对象类型
	 * @return 转换的目标对象
	 * @throws Exception 转换失败
	 */
	public static <T> T xmlToBean(Element rootElt, Class<T> pojo)
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		// 首先得到pojo所定义的字段
		Field[] fields = pojo.getDeclaredFields();
		// 根据传入的Class动态生成pojo对象
		T obj = pojo.getConstructor().newInstance();
		for (Field field : fields) {
			// 设置字段可访问（必须，否则报错）
			field.setAccessible(true);
			// 得到字段的属性名
			String name = field.getName();
			// 这一段的作用是如果字段在Element中不存在会抛出异常，如果出异常，则跳过。
			try {
				rootElt.elementTextTrim(name);
			}
			catch (Exception ex) {
				continue;
			}
			// 如果根据 Key 拿到的值为空，则跳过，进行下一轮循环
			if (StrUtil.isEmpty(rootElt.elementTextTrim(name))) {
				continue;
			}

			// 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
			if (field.getType().equals(String.class)) {
				field.set(obj, rootElt.elementTextTrim(name));
			}
			else if (field.getType().equals(java.util.Date.class)) {
				field.set(obj, Date.parse(rootElt.elementTextTrim(name)));
			}
			else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
				field.set(obj, Boolean.parseBoolean(rootElt.elementTextTrim(name)));
			}
			else if (field.getGenericType().equals(List.class)) {
				System.out.println("这是一个list");
			}

		}
		return obj;
	}

}
