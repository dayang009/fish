package com.fish.common.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

public class YangUtil {

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

}
