package com.recykling.report.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ExtractExpirationDate {
    private final ExtractClaim extractClaim;
    public Date extract(String token){
        return extractClaim.extract(token, Claims::getExpiration);
    }
}
