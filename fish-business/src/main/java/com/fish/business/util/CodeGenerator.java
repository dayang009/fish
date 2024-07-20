package com.fish.business.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

public class CodeGenerator {

	public static void main(String[] args) {
		FastAutoGenerator.create("jdbc:postgresql://dayang.com:5432/postgres", "postgres", "postgres")
			.globalConfig(builder -> builder.author("dayang")
				.outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
				.dateType(DateType.TIME_PACK)
				.commentDate("yyyy-MM-dd HH:mm:ss"))
			.packageConfig(builder -> builder.parent("com.baomidou.mybatisplus")
				.entity("entity")
				.mapper("mapper")
				.service("service")
				.serviceImpl("service.impl")
				.xml("mapper.xml"))
			.strategyConfig(builder -> builder.entityBuilder().enableLombok())
			.templateEngine(new FreemarkerTemplateEngine())
			.execute();
	}

}
