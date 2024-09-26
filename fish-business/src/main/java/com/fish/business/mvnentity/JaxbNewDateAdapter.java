package com.fish.business.mvnentity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;

/**
 * 定制{@link LocalDateTime}类型序列化方式
 *
 * @author dayang
 */
public final class JaxbNewDateAdapter extends XmlAdapter<String, LocalDateTime> {

	@Override
	public LocalDateTime unmarshal(String v) {
		if (v == null) {
			return null;
		}
		return LocalDateTimeUtil.parse(DatePattern.NORM_DATETIME_PATTERN);
	}

	@Override
	public String marshal(LocalDateTime v) {
		return LocalDateTimeUtil.format(v, DatePattern.NORM_DATETIME_PATTERN);
	}

}
