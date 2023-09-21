package com.fish.flow;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * @author dayang
 * @since 2023/8/19
 */
public class HanShuTest {

	Function<String, Integer> demoDeploy = str -> {
		System.out.println(str);
		return 666999;
	};

	public static <R, ID> R handleData(ID id, Function<ID, R> dbFallback) {
		System.out.println("业务1");

		R result = dbFallback.apply(id);

		System.out.println("业务3");

		return result;
	}

	@Test
	public void demoTest() {
		Function<Integer, String> function = id -> {
			System.out.println("业务2...");
			return "test" + id;
		};
		String s = handleData(888, function);
		System.out.println(s);
	}

}
