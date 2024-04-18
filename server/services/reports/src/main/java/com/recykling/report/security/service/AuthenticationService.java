package com.recykling.report.security.service;


import ch.qos.logback.core.subst.Token;
import com.recykling.report.security.controller.request.LoginRequest;
import com.recykling.report.security.controller.request.RegisterRequest;
import com.recykling.report.security.controller.response.AuthenticationResponse;
import com.recykling.report.security.user.User;
import com.recykling.report.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User createdUser = userRepository.save(user);

        String token = jwtService.generateToken(createdUser);
        Date date = jwtService.extractExpirationDate(token);

        return new AuthenticationResponse(token, date);
    }

    public AuthenticationResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);
        Date expirationDate = jwtService.extractExpirationDate(token);

        return new AuthenticationResponse(token, expirationDate);
    }
}
