package com.fish.common.core.config;

import cn.hutool.core.date.DatePattern;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 处理 LocalDateTime 序列化与反序列化
 */
public final class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

	@Override
	public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
	}

	@Override
	public LocalDateTime deserialize(JsonElement element, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		String timestamp = element.getAsJsonPrimitive().getAsString();
		return LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
	}

}
