package com.recykling.report.security.service;

import com.recykling.report.security.user.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public interface IJwtService {
    /**
     * Based on user object function is going to create JWT token with users username subject.
     *
     * @param user - Input user object.
     * @return - Return JWT token.
     */
    String generateToken(User user);

    /**
     *
     * @return - Function returns SecretKey coded in BASE64.
     */
    SecretKey getSignKey();

    /**
     * Function extracting full payload from token, needs to be verified with SignKey.
     * Based on this payload, other function can extract subject with is username in this case or expiration date.
     *
     * @param token - Input JWT token.
     * @return - Returns payload in Claims format.
     */
    Claims extractPayload(String token);

    /**
     *  Extracts data from a JWT token and transforms it using the provided resolver function.
     *
     * @param token - Input JWT token.
     * @param resolver - The function that transforms a Claims object into a value of type T.
     * @param <T> - The type of the value returned by the resolver.
     * @return - The value transformed by the resolver.
     */
    <T> T extract(String token, Function<Claims, T> resolver);

    /**
     * Extracts the expiration date from a JWT token.
     *
     * @param token - Input JWT token.
     * @return - The expiration date extracted from the token.
     */
    Date extractExpirationDate(String token);

    /**
     * Extracts the username date from a JWT token.
     *
     * @param token - Input JWT token.
     * @return - The username extracted from the token.
     */
    String extractUsername(String token);

    /**
     * Checks if a JWT token is valid for a given user.
     *
     * @param token - Input JWT token.
     * @param userDetails - The UserDetails object representing the user.
     * @return - True if the token is valid for the user.
     */
    Boolean isValid(String token, UserDetails userDetails);

    /**
     * Checks if a JWT token is expired for a given user.
     *
     * @param token - Input JWT token.
     * @return - True if the token IS EXPIRED for the user.
     */
    Boolean isTokenExpired(String token);
}
