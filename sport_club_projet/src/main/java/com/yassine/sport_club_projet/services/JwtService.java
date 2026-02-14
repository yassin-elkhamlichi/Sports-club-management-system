package com.yassine.sport_club_projet.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${app.jwt.expiration}")
    private long tokenExpirationTime;
    @Value("${app.jwt.secret}")
    private String secretKey;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, HttpServletRequest request) {
        return Jwts.builder()
                .subject(email)
                .claim("user_ip", request.getRemoteAddr())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpirationTime))
                .signWith(signingKey)
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extractClaim(String tokenWithoutBearer, String userIp) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(tokenWithoutBearer)
                .getPayload().get(userIp, String.class);
    }
}
