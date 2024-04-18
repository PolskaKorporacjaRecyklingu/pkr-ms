package com.recykling.report.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidToken {
    private final ExtractUsername extractUsername;
    private final IsTokenExpired isTokenExpired;
    public boolean isValid(String token, UserDetails user){
        String username = extractUsername.extract(token);
        return username.equals(user.getUsername()) && !isTokenExpired.check(token);
    }
}
