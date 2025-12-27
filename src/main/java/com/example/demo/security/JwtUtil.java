package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
 

    private static final String DEFAULT_SECRET =
            "default-test-secret-key-should-be-at-least-32-bytes!";
    private static final long DEFAULT_EXPIRATION_MS = 60 * 60 * 1000; // 1 hour
 

    private Key key;
    private long expirationMs = DEFAULT_EXPIRATION_MS;

     
    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes(StandardCharsets.UTF_8));
    }
 
    public JwtUtil(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
 
    public JwtUtil(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms}") long expirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }
 

    public String generateToken(Map<String, Object> claims, String subject) {

        if (claims == null) {
            claims = new HashMap<>();
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(getUsername(token))
                && !getClaims(token).getExpiration().before(new Date());
    } 
 
    public long getExpirationMillis() {
        return expirationMs;
    }
}
