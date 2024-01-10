package com.fish.business.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScheduCacheMap<String, ScheduledFuture> extends LinkedHashMap<String, ScheduledFuture> {

	private static final long serialVersionUID = 5006498516101350681L;

	// 指定容量
	private static final int MAX_ENTRIES = 5;

	// 在构造函数中指定accessOrder为true，以便使用访问顺序而不是插入顺序
	public ScheduCacheMap() {
		super(MAX_ENTRIES, 0.75F, true);
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<String, ScheduledFuture> eldest) {
		// 当达到指定容量时，删除最先添加的键值对
		return size() > MAX_ENTRIES;
	}

}
