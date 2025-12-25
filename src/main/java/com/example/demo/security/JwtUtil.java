public String generateToken(Map<String, Object> claims, String subject) {

    if (claims == null) {
        claims = new HashMap<>();
    }

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date())
            .setExpiration(
                new Date(System.currentTimeMillis() + expirationMs)
            )
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
}
