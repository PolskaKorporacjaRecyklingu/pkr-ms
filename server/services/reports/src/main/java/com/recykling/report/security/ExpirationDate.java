package com.recykling.report.security;

import com.recykling.report.security.config.SecurityConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ExpirationDate {
    private final SecurityConstant securityConstant;
    public Date getExpirationDate(){
        return new Date(System.currentTimeMillis() + securityConstant.TOKEN_DURATION);
    }
}
