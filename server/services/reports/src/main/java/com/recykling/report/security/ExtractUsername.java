package com.recykling.report.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtractUsername {
    private final ExtractClaim extractClaim;
    public String extract(String token){
        return extractClaim.extract(token, Claims::getSubject);
    }
}
