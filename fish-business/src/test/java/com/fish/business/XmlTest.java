package com.fish.business;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlTest {

	@Test
	void demo01() throws DocumentException {

		String filePath = File.separator + "temp" + File.separator;
		String fileName = "settings.xml";

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filePath + fileName));

		String w1 = "看动画，学编程";
		Path path = Paths.get("C:/Users/dayang/.m2/test.txt");

		// Files.write(path, w1.getBytes(), StandardOpenOption.APPEND,
		// StandardOpenOption.CREATE);
		File file = new File("src");
		System.out.println(file.getAbsolutePath());

	}

}
