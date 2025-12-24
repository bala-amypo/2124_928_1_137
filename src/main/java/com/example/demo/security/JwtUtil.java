package com.example.demo.security;

import java.util.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class JwtUtil {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationMs = 3600000;

    public JwtUtil() {}

    public String generateToken(Map<String,Object> claims, String subject) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
            .signWith(key)
            .compact();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
            .parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, String username) {
        return getUsername(token).equals(username)
            && getClaims(token).getExpiration().after(new Date());
    }

    public long getExpirationMillis() {
        return expirationMs;
    }
}
