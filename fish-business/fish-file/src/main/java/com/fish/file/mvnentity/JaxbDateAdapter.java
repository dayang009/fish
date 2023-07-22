package com.fish.file.mvnentity;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定制{@link Date}类型序列化方式
 *
 * @author dayang
 */
public class JaxbDateAdapter extends XmlAdapter<String, Date> {

	private static final String STD_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public Date unmarshal(String v) throws Exception {
		if (v == null) {
			return null;
		}
		return new SimpleDateFormat(STD_DATE_FORMAT).parse(v);
	}

	@Override
	public String marshal(Date v) {
		return new SimpleDateFormat(STD_DATE_FORMAT).format(v);
	}

}
