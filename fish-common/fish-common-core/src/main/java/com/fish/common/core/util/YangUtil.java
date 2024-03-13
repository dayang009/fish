package com.fish.common.core.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fish.common.core.config.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

public class YangUtil {

	private static final String LOCAL_IP = "127.0.0.1";

	public static String dateToCron(Date date) {

		StringBuilder str = new StringBuilder();
		str.append(DateUtil.second(date));
		str.append(" ");
		str.append(DateUtil.minute(date));
		str.append(" ");
		str.append(DateUtil.hour(date, true));
		str.append(" ");
		str.append(DateUtil.dayOfMonth(date));
		str.append(" ");
		str.append(DateUtil.month(date) + 1);
		str.append(" ? ");
		str.append(DateUtil.year(date));
		str.append("-");
		str.append(DateUtil.year(date));

		return StrUtil.str(str);
	}

	public static Gson getGson() {

		return new GsonBuilder().setPrettyPrinting()
			.setDateFormat(DatePattern.NORM_DATETIME_PATTERN)
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
			.create();
	}

	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return "unknown";
		}
		String ip = request.getHeader("x-forwarded-for");
		return "0.0.0.0";
	}

}
