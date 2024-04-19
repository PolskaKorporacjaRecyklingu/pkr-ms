package com.recykling.report.security.service.impl;

import com.recykling.report.security.config.SecurityConstant;
import com.recykling.report.security.service.IJwtService;
import com.recykling.report.security.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements IJwtService {

    private final SecurityConstant securityConstant;

    /**
     * Based on user object function is going to create JWT token with users username subject.
     *
     * @param user - Input user object.
     * @return - Return JWT token.
     */
    @Override
    public String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + securityConstant.TOKEN_DURATION))
                .signWith(getSignKey())
                .compact();
    }

    /**
     * @return - Function returns SecretKey coded in BASE64.
     */
    @Override
    public SecretKey getSignKey() {
        byte[] keyBytes = Decoders
                .BASE64URL
                .decode(securityConstant.SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Function extracting full payload from token, needs to be verified with SignKey.
     * Based on this payload, other function can extract subject with is username in this case or expiration date.
     *
     * @param token - Input JWT token.
     * @return - Returns payload in Claims format.
     */
    @Override
    public Claims extractPayload(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extracts data from a JWT token and transforms it using the provided resolver function.
     *
     * @param token    - Input JWT token.
     * @param resolver - The function that transforms a Claims object into a value of type T.
     * @return - The value transformed by the resolver.
     */
    @Override
    public <T> T extract(String token, Function<Claims, T> resolver) {
        Claims claims = extractPayload(token);
        return resolver.apply(claims);
    }

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token - Input JWT token.
     * @return - The expiration date extracted from the token.
     */
    @Override
    public Date extractExpirationDate(String token) {
        return extract(token, Claims::getExpiration);
    }

    /**
     * Extracts the username date from a JWT token.
     *
     * @param token - Input JWT token.
     * @return - The username extracted from the token.
     */
    @Override
    public String extractUsername(String token) {
        return extract(token, Claims::getSubject);
    }

    /**
     * Checks if a JWT token is valid for a given user.
     *
     * @param token       - Input JWT token.
     * @param userDetails - The UserDetails object representing the user.
     * @return - True if the token is valid for the user.
     */
    @Override
    public Boolean isValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Checks if a JWT token is expired for a given user.
     *
     * @param token - Input JWT token.
     * @return - True if the token IS EXPIRED for the user.
     */
    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpirationDate(token)
                .before(new Date());
    }
}
