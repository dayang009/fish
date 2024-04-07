package com.fish.business;

import cn.hutool.core.date.DatePattern;
import com.fish.business.entity.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StreamDemo01Test {

	Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(DatePattern.NORM_DATETIME_PATTERN).create();

	@Test
	public void demo01() {

		List<Student> demoList = this.getDemoList();

		// 按照班级分组
		Map<String, List<Student>> c1 = demoList.stream().collect(Collectors.groupingBy(Student::getClassNumber));
		System.out.println("按照班级分组");
		System.out.println(gson.toJson(c1));

		Map<String, List<String>> c2 = demoList.stream()
			.collect(Collectors.groupingBy(Student::getClassNumber,
					Collectors.mapping(Student::getName, Collectors.toList())));
		System.out.println("按照班级分组得到每个班级的同学姓名");
		System.out.println(gson.toJson(c2));

		Map<String, Long> c3 = demoList.stream()
			.collect(Collectors.groupingBy(Student::getClassNumber, Collectors.counting()));
		System.out.println("统计每个班级人数");
		System.out.println(gson.toJson(c3));

		Map<String, Double> c4 = demoList.stream()
			.collect(Collectors.groupingBy(Student::getClassNumber, Collectors.averagingInt(Student::getMathScores)));
		System.out.println("求每个班级的数学平均成绩");
		System.out.println(gson.toJson(c4));

		Map<String, Map<String, Integer>> c5 = demoList.stream()
			.collect(Collectors.groupingBy(Student::getClassNumber, Collectors.toMap(Student::getName,
					student -> student.getChainessScores() + student.getMathScores())));

		System.out.println("按班级分组求每个同学的总成绩");
		System.out.println(gson.toJson(c5));
	}

	@Test
	public void demo02() {
		Map<String, String> treeMap = new TreeMap<>();
		treeMap.put("C", "C");
		treeMap.put("E", "E");
		treeMap.put("D", "D");
		treeMap.put("B", "B");
		treeMap.put("A", "A");
		treeMap.forEach((s1, s2) -> System.out.println("key: " + s1 + ", value: " + s2));
	}

	@Test
	void demo03() {
		System.out.println("嵌套分组，先按班级分组，再按年龄分组");
		List<Student> demoList = this.getDemoList();
		Map<String, Map<Integer, List<Student>>> collect = demoList.stream()
			.collect(Collectors.groupingBy(Student::getClassNumber, Collectors.groupingBy(Student::getAge)));
		System.out.println(gson.toJson(collect));
	}

	@Test
	void demo04() {
		List<Student> demoList = this.getDemoList();
		System.out.println("根据年龄分组并小到大排序,TreeMap默认为按照key升序");
		TreeMap<Integer, List<String>> collect = demoList.stream()
			.collect(Collectors.groupingBy(Student::getAge, TreeMap::new,
					Collectors.mapping(Student::getName, Collectors.toList())));
		System.out.println(gson.toJson(collect));

	}

	private List<Student> getDemoList() {
		Student s1 = new Student("001", "zhangSan", 18, "1701", "北京", 88, 95);
		Student s2 = new Student("002", "liSi", 28, "1701", "上海", 88, 95);
		Student s3 = new Student("003", "wangWu", 19, "1702", "石家庄", 88, 95);
		Student s4 = new Student("004", "zhaoLiu", 18, "1701", "北京", 88, 95);
		Student s5 = new Student("005", "zhouQi", 9, "1703", "北京", 88, 95);
		List<Student> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		return list;
	}

}
