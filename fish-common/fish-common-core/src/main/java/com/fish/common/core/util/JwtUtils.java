package com.fish.common.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author dayang
 * @date 2023/4/27
 */
public class JwtUtils {

	public static String generatorToken(Map<String, String> map) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		Date date = calendar.getTime();

		JWTCreator.Builder builder = JWT.create();

		map.forEach(builder::withClaim);

		// 整合过期时间
		builder.withExpiresAt(date);

		// 生成Token
		String sign = builder.sign(Algorithm.HMAC256("ABC123"));

		return sign;
	}

	public static void main(String[] args) {
		// Map<String, String> map = new HashMap<>();
		// map.put("name", "zhangSan");
		// map.put("age", "18");
		// String s = generatorToken(map);
		// String token = JwtTokenGenerator.generateToken(SECRE_KEY, "subject", 100000,
		// map);
		// System.out.println(token);
		// DecodedJWT decodedJWT = JwtTokenParser.parseToken(token, SECRE_KEY);
		// System.out.println(decodedJWT.getClaim("name"));
		System.out.println(UUID.randomUUID());
	}

}
