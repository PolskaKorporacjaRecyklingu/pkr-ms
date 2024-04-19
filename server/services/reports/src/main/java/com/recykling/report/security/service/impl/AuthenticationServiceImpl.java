package com.recykling.report.security.service.impl;

import com.recykling.report.exception.ResourceNotFoundException;
import com.recykling.report.exception.dto.UniqueUsernameException;
import com.recykling.report.security.controller.request.LoginRequest;
import com.recykling.report.security.controller.request.RegisterRequest;
import com.recykling.report.security.controller.response.AuthenticationResponse;
import com.recykling.report.security.service.IAuthenticationService;
import com.recykling.report.security.service.IJwtService;
import com.recykling.report.security.user.User;
import com.recykling.report.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService iJwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Function registering user
     * *(only authenticated ADMIN can register new users)
     * *(do not return token of created user)
     *
     * @param request - RegisterRequest object with data of firstName, lastName, username, password, role.
     */
    @Override
    public void register(RegisterRequest request) {
        Optional<User> checkUser = userRepository.findByUsername(request.getUsername());
        if (checkUser.isPresent()){
            throw new UniqueUsernameException(request.getUsername());
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
    }

    /**
     * @param request - LoginRequest object with username and password.
     * @return - Returns AuthenticationResponse with contains JWT token and expiration date.
     */
    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = iJwtService.generateToken(user);
        Date expirationDate = iJwtService.extractExpirationDate(token);

        return new AuthenticationResponse(token, expirationDate);
    }

    /**
     * @param username - Username of user to delete.
     */
    @Override
    public void deleteUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new ResourceNotFoundException("user", "username", username);
        } else {
            userRepository.delete(user.get());
        }
    }
}
