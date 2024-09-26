package com.fish.common.core.util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fish.common.core.constant.GlobalConstant;

import java.util.Date;
import java.util.Map;

/**
 * @author dayang
 * @since 2023/4/27
 */
public class JwtUtils {

	public static String generatorToken(Map<String, String> map) {

		JWTCreator.Builder builder = JWT.create();

		map.forEach(builder::withClaim);

		// 整合过期时间
		builder.withExpiresAt(DateUtil.offsetMinute(new Date(), 10));

		// 生成Token
		String sign = builder.sign(Algorithm.HMAC256(GlobalConstant.SALT));

		return sign;
	}

	public static DecodedJWT parseToken(String token) {
		return JWT.require(Algorithm.HMAC256(GlobalConstant.SALT)).build().verify(token);
	}

	public static void main(String[] args) {

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYWNrTmFtZSI6InpoYW5nU2FuIiwiaWQiOiI4ODg4IiwidGFnIjoie30iLCJleHAiOjE3MjIzMjcxMjR9.1dUzEJOBj6M6Q6lus0IlxPBnpaYSqRRcAjH6lXcApm8";
		DecodedJWT decodedJWT = JwtUtils.parseToken(token);
		System.out.println("decodedJWT.getPayload() = " + decodedJWT.getPayload());
		System.out.println(decodedJWT.getClaim("id").asString());

	}

}
