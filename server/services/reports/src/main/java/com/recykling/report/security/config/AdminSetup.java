package com.recykling.report.security.config;

import com.recykling.report.security.controller.request.RegisterRequest;
import com.recykling.report.security.service.impl.AuthenticationServiceImpl;
import com.recykling.report.security.user.Role;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSetup implements CommandLineRunner {
    private final AuthenticationServiceImpl authenticationService;
    private final AdminAccountData adminAccountData;

    @Override
    public void run(String... args){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setRole(Role.ADMIN);
        registerRequest.setFirstName(adminAccountData.getFirstname());
        registerRequest.setLastName(adminAccountData.getLastname());
        registerRequest.setUsername(adminAccountData.getUsername());
        registerRequest.setPassword(adminAccountData.getPassword());

        authenticationService.register(registerRequest);
    }
}
