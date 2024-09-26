package com.fish.business.mvnentity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Date;

/**
 * 定制{@link Date}类型序列化方式
 *
 * @author dayang
 */
public final class JaxbDateAdapter extends XmlAdapter<String, Date> {

	@Override
	public Date unmarshal(String v) {
		if (v == null) {
			return null;
		}
		return DateUtil.parse(v, DatePattern.NORM_DATETIME_PATTERN);
	}

	@Override
	public String marshal(Date v) {
		return DateUtil.format(v, DatePattern.NORM_DATETIME_PATTERN);
	}

}
