package com.recykling.report.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ExtractAllClaims {
    private final KeyWriter keyWriter;
    public Claims extract(String token) {
        return Jwts
                .parser()
                .verifyWith(keyWriter.getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
