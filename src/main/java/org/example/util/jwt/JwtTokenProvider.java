package org.example.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.example.util.exception.ResponseCustomStatusException;
import org.example.util.jwt.req.ReqGenerateToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretString;

    private SecretKey key;

    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
    private final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 14;// 2주

    @PostConstruct
    public void init() {
        byte[] keyBytes = secretString.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public Map<String, String> generateTokens(String userEmail, long userId) {
        Map<String, String> tokens = new HashMap<>();

        // Access Token 생성
        ReqGenerateToken accessTokenPayload = new ReqGenerateToken(userEmail, userId, EXPIRATION_TIME, "access");
        String accessToken = generateToken(accessTokenPayload);

        // Refresh Token 생성
        ReqGenerateToken refreshTokenPayload = new ReqGenerateToken(userEmail, userId, REFRESH_TOKEN_EXPIRATION_TIME, "refresh");
        String refreshToken = generateToken(refreshTokenPayload);

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    public String refreshAccessToken(String refreshToken) {
        // Refresh Token 검증
        if (!validateToken(refreshToken)) {
            throw new ResponseCustomStatusException("Invalid refresh token", HttpStatus.UNAUTHORIZED);
        }

        // 토큰에서 정보 추출
        Claims claims = extractAllClaims(refreshToken);

        // Refresh Token 인지 확인
        String tokenType = claims.get("tokenType", String.class);
        if (!"refresh".equals(tokenType)) {
            throw new ResponseCustomStatusException("Token is not a refresh token", HttpStatus.UNAUTHORIZED);
        }

        // 사용자 이름 추출
        String userEmail = claims.getSubject();
        long userId = Long.parseLong(claims.get("id").toString());

        // 새로운 Access Token 발급
        ReqGenerateToken accessTokenPayload = new ReqGenerateToken(userEmail, userId, EXPIRATION_TIME, "access");
        return generateToken(accessTokenPayload);
    }

    // 토큰 생성
    public String generateToken(ReqGenerateToken payload) {
        return Jwts.builder()
                .subject(payload.getUserEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + payload.getExpirationTime()))
                .claim("tokenType", payload.getTokenType())
                .claim("id", payload.getUserId())
                .signWith(key)
                .compact();
    }
    
    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long extractUserIdClaims(String token) {
        Claims claims = extractAllClaims(token);

        return Long.parseLong(claims.get("id").toString());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
