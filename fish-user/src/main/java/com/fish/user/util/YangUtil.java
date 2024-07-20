package com.fish.user.util;

import cn.hutool.core.date.DatePattern;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class YangUtil {

	public static Gson getGson() {
		return new GsonBuilder().disableHtmlEscaping()
			.disableInnerClassSerialization()
			.setDateFormat(DatePattern.NORM_DATETIME_PATTERN)
			.serializeNulls()
			.create();
	}

}
