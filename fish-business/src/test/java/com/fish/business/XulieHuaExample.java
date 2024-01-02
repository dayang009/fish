package com.fish.business;

import io.fury.Fury;
import io.fury.ThreadLocalFury;
import io.fury.ThreadSafeFury;
import io.fury.config.Language;

public class XulieHuaExample {

	public static void main(String[] args) {
		Student object = new Student("888999", "zhangSan张三", 18);

		// 注意应该在多次序列化之间复用Fury实例
		{
			Fury fury = Fury.builder()
				.withLanguage(Language.JAVA)
				// 允许反序列化未知类型，如果未知类型包含恶意代码则会有安全风险
				.requireClassRegistration(false)
				.build();
			// 注册类型可以减少类名称序列化，但不是必须的。
			// 如果安全模式开启(默认开启)，所有自定义类型必须注册。
			fury.register(Student.class);
			byte[] bytes = fury.serialize(object);
			System.out.println(fury.deserialize(bytes));
		}
		{
			ThreadSafeFury fury = Fury.builder()
				.withLanguage(Language.JAVA)
				// 允许反序列化未知类型，如果未知类型包含恶意代码则会有安全风险
				.requireClassRegistration(false)
				.buildThreadSafeFury();
			byte[] bytes = fury.serialize(object);
			System.out.println(fury.deserialize(bytes));
		}
		{
			ThreadSafeFury fury = new ThreadLocalFury(classLoader -> {
				Fury f = Fury.builder().withLanguage(Language.JAVA).withClassLoader(classLoader).build();
				f.register(Student.class);
				return f;
			});
			byte[] bytes = fury.serialize(object);
			System.out.println(fury.deserialize(bytes));
		}
	}

}
