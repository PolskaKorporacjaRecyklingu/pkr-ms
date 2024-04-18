package com.recykling.report.security.controller;


import com.recykling.report.security.controller.request.LoginRequest;
import com.recykling.report.security.controller.request.RegisterRequest;
import com.recykling.report.security.controller.response.AuthenticationResponse;
import com.recykling.report.security.service.AuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
        private final AuthenticationService authService;


        @PostMapping(path = "/register")
        public ResponseEntity<AuthenticationResponse> register(
                @Valid @NotNull @RequestBody RegisterRequest request
                ){
            AuthenticationResponse authenticationResponse = authService.register(request);
            return ResponseEntity.status(HttpStatus.OK).body(authenticationResponse);
        }

        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse> login(
                @Valid @NotNull @RequestBody LoginRequest request
        ){
            return ResponseEntity.status(HttpStatus.OK).body(
                    authService.authenticate(request)
            );
        }
}
