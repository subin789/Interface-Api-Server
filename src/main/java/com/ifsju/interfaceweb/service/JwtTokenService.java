package com.ifsju.interfaceweb.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenService {
    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenService.class);

    @Value("${jwt.secret}")
    private String secretKey = "01234567defaultSecretKey01234567";
    private final Long tokenValidMillisecond = 60 * 60 * 1000L;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        LOGGER.info("[init] secretKey 초기화 완료");
    }

    public String createToken(String email){
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        LOGGER.info("[createToken] 토큰 생성 완료");
        return token;
    }

    public boolean isValidatedToken(String token){
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            LOGGER.info("[isValidatedToken] 유효 하지 않은 토큰");
            return false;
        }
    }

    public String resolveToken(HttpServletRequest httpServletRequest){
        LOGGER.info("[resolveToken] 토크 값 추출");
        return httpServletRequest.getHeader("X-AUTH-TOKEN");
    }



}
