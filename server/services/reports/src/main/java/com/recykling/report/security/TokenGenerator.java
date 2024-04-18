package com.recykling.report.security;


import com.recykling.report.security.user.User;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private final KeyWriter keyWriter;
    private final ExpirationDate expirationDate;

    public String generateToken(User user){
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate.getExpirationDate())
                .signWith(keyWriter.getSignKey())
                .compact();
    }
}
