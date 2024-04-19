package com.recykling.report.security.service;

import com.recykling.report.security.controller.request.LoginRequest;
import com.recykling.report.security.controller.request.RegisterRequest;
import com.recykling.report.security.controller.response.AuthenticationResponse;

public interface IAuthenticationService {
    /**
     * Function registering user
     *      *(only authenticated ADMIN can register new users)
     *      *(do not return token of created user)
     *
     * @param request - RegisterRequest object with data of firstName, lastName, username, password, role.
     */
    void register(RegisterRequest request);

    /**
     *
     * @param request - LoginRequest object with username and password.
     * @return - Returns AuthenticationResponse with contains JWT token and expiration date.
     */
    AuthenticationResponse authenticate(LoginRequest request);

    /**
     *
     * @param username - Username of user to delete.
     */
    void deleteUser(String username);
}
