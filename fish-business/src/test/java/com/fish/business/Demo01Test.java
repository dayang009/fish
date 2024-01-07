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

	@Test
	public void thenCombine() {
		SmallTool.printTimeAndThread("zhangSan");
		SmallTool.printTimeAndThread("zhangSan 点了 米饭 和 菜");

		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("厨师炒菜中。。。");
			SmallTool.sleepMillis(800);
			return "菜炒好了";

		}).thenCombine(CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("服务员蒸饭");
			SmallTool.sleepMillis(500);
			return "米饭蒸好了";
		}), (dish, rice) -> {
			SmallTool.printTimeAndThread("服务员打饭");
			SmallTool.sleepMillis(100);
			return String.format("%s + %s 好了", dish, rice);
		});

		SmallTool.printTimeAndThread("zhangSan Gameing");
		SmallTool.printTimeAndThread(String.format("%s , zhagnSan eat", cf1.join()));

	}

	@Test
	public void demo04() {
		SmallTool.printTimeAndThread("zhangSan 准备回家");

		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("700路车正在赶来");
			SmallTool.sleepMillis(100L);
			return "700路到了";
		}).applyToEither(CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("800路车在路上");
			SmallTool.sleepMillis(200L);
			return "800路车到了";
		}), firstBus -> {
			SmallTool.printTimeAndThread(firstBus);
			if (firstBus.startsWith("700")) {
				throw new RuntimeException("车撞树了");
			}
			return firstBus;
		}).exceptionally(e -> {
			SmallTool.printTimeAndThread(e.getMessage());
			SmallTool.printTimeAndThread("zhanSan叫出租车");
			return "出租车叫到了";
		});

		SmallTool.printTimeAndThread(String.format("%s , zhagnSan 坐车回家", cf2.join()));

	}

	@Test
	public void demo05() {
		SmallTool.printTimeAndThread("zhangSan 准备回家");

		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("700路车正在赶来");
			SmallTool.sleepMillis(100L);
			return "700路到了";
		}).exceptionally().applyToEither(CompletableFuture.supplyAsync(() -> {
			SmallTool.printTimeAndThread("800路车在路上");
			SmallTool.sleepMillis(200L);
			return "800路车到了";
		}), firstBus -> {
			SmallTool.printTimeAndThread(firstBus);
			if (firstBus.startsWith("700")) {
				throw new RuntimeException("车撞树了");
			}
			return firstBus;
		}).exceptionally(e -> {
			SmallTool.printTimeAndThread(e.getMessage());
			SmallTool.printTimeAndThread("zhanSan叫出租车");
			return "出租车叫到了";
		});

		SmallTool.printTimeAndThread(String.format("%s , zhagnSan 坐车回家", cf2.join()));

	}

}
