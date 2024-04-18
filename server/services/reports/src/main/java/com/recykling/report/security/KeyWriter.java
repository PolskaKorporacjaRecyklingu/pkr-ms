package com.recykling.report.security;



import com.recykling.report.security.config.SecurityConstant;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@RequiredArgsConstructor
public class KeyWriter {
    private final SecurityConstant securityConstant;
    public SecretKey getSignKey(){
        byte[] keyBytes = Decoders
                .BASE64URL
                .decode(securityConstant.SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
