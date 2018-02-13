package br.com.rft.peculium.util;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "adonis-token";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";

	private JWTUtil() {
	}

	public static String createToken(String email) {
		String jwtToken = Jwts.builder().setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + JWTUtil.EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, JWTUtil.SECRET).compact();
		return JWTUtil.TOKEN_PREFIX + " " + jwtToken;
	}

}
