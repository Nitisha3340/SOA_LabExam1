
package com.klu.auth_service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String secret =
        "VGhpc0lzQVN1cGVyU2VjcmV0S2V5Rm9ySldUQXV0aGVudGljYXRpb24xMjM0NTY=";

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(secret)
        );
    }

    public String generateToken(String username) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date(now))
            .expiration(new Date(now + 60 * 60 * 1000))
            .signWith(getKey())
            .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
