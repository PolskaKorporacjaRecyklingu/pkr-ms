package com.recykling.report.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ExtractClaim {
    private final ExtractAllClaims extractAllClaims;
    public <T> T extract(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims.extract(token);
        return resolver.apply(claims);
    }
}
