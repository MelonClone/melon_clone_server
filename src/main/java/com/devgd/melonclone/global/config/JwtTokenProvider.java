package com.devgd.melonclone.global.config;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.devgd.melonclone.domain.user.application.UserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secret;
	private Key secretKey;
	
	// 토큰 유효시간 30분
	private long tokenValidTime = 30 * 60 * 1000L;
	
	private final UserService userService;

	// 객체 초기화, secretKey를 hmac sha 로 인코딩한다.
	@PostConstruct
	protected void init() {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}

	// JWT 토큰 생성
	public String createToken(String userId, Role role, Map<String, String> publicInfo) {
		Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위
		claims.put("roles", role); // 공개할 역할 (ex 권한 정보)
		claims.put("info", publicInfo); // 공개할 정보 (ex 사용자 정보)
		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims) // 정보 저장
				.setIssuedAt(now) // 토큰 발행 시간 정보
				.setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
				.signWith(secretKey, SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘과 secret값 세팅
				.compact();
	}

	// JWT 토큰에서 인증 정보 조회
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userService.getUserById(Integer.valueOf(this.getUserId(token)));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public JwtParser getJwtParser() {
		return Jwts.parserBuilder().setSigningKey(secretKey).build();
	}

	// 토큰에서 회원 정보 추출
	public String getUserId(String token) {
		String userId = getClaims(token).getSubject();
		return userId;
	}

	// Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
	public String resolveToken(HttpServletRequest request) {
		if (request.getHeader("Authorization") != null) return request.getHeader("Authorization").substring("Bearer ".length());
		if (request.getHeader("X-AUTH-TOKEN") != null) return request.getHeader("X-AUTH-TOKEN");

		return "";
	}

	// 토큰의 유효성 + 만료일자 확인
	public boolean validateToken(String jwtToken) {
		try {
			return !getClaims(jwtToken).getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public Claims getClaims(String jwt) {
		try {
			return getJwtParser().parseClaimsJws(jwt).getBody();
		} catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
				| IllegalArgumentException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}
}