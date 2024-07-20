package com.fish.business.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Queue;

public class TestMain {

	public static ObjectMapper mapper = new ObjectMapper();

	static {
		// configure方法 配置一些需要的参数
		// 转换为格式化的json 显示出来的格式美化
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		// 序列化的时候序列对象的那些属性
		// JsonInclude.Include.NON_DEFAULT 属性为默认值不序列化
		// JsonInclude.Include.ALWAYS 所有属性
		// JsonInclude.Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
		// JsonInclude.Include.NON_NULL 属性为NULL 不序列化
		mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

		// 反序列化时,遇到未知属性会不会报错
		// true - 遇到没有的属性就报错 false - 没有的属性不会管，不会报错
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// 如果是空对象的时候,不抛异常
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		// 忽略 transient 修饰的属性
		mapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

		// 修改序列化后日期格式
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		// 处理不同的时区偏移格式
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
	}

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<>();
		queue.offer("aaa");
		queue.offer("bbb");
		queue.offer("ccc");

		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}

}
