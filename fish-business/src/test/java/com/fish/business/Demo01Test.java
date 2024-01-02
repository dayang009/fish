package com.fish.business;

import com.fish.business.util.SmallTool;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class Demo01Test {

	@Test
	public void demo01() {
		SmallTool.printTimeAndThread("zhangSan");
		SmallTool.printTimeAndThread("zhangSan 点了 米饭 和 菜");

		CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("厨师做饭中。。。");
			SmallTool.sleepMillis(800);
			SmallTool.printTimeAndThread("小2打饭中。。。");
			SmallTool.sleepMillis(200);
			return "饭做好并且打好了";
		});

		SmallTool.printTimeAndThread("zhangSan Gameing");
		SmallTool.printTimeAndThread(String.format("%s , zhagnSan eat", result.join()));

	}

	@Test
	public void demo02() {
		SmallTool.printTimeAndThread("zhangSan");
		SmallTool.printTimeAndThread("zhangSan 点了 米饭 和 菜");

		CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("厨师炒菜中。。。");
			SmallTool.sleepMillis(800);
			return "菜炒好了";

		}).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("xiao2 da fan");
			SmallTool.sleepMillis(200);
			return dish + "米饭好了";
		}));

		SmallTool.printTimeAndThread("zhangSan Gameing");
		SmallTool.printTimeAndThread(String.format("%s , zhagnSan eat", result.join()));

	}

}
