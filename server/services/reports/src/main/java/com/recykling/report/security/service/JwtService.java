package com.recykling.report.security.service;


import com.recykling.report.security.ExtractClaim;
import com.recykling.report.security.ExtractExpirationDate;
import com.recykling.report.security.TokenGenerator;
import com.recykling.report.security.ValidToken;
import com.recykling.report.security.user.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class JwtService {
    private final TokenGenerator tokenGenerator;
    private final ExtractClaim extractClaim;
    private final ValidToken validToken;
    private final ExtractExpirationDate extractExpirationDate;

    public boolean isValid(String token, UserDetails user){
        return validToken.isValid(token,user);
    }

    public String extractUsername(String token){
        return extractClaim.extract(token, Claims::getSubject);
    }
    public String generateToken(User user){
        return tokenGenerator.generateToken(user);
    }
    public Date extractExpirationDate(String token){return extractExpirationDate.extract(token);}

}
