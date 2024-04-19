package com.recykling.report.security.controller;


import com.recykling.report.exception.dto.ResponseDto;
import com.recykling.report.security.controller.request.LoginRequest;
import com.recykling.report.security.controller.request.RegisterRequest;
import com.recykling.report.security.controller.response.AuthenticationResponse;
import com.recykling.report.security.service.IAuthenticationService;
import com.recykling.report.security.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
        private final IAuthenticationService iAuthenticationService;

        @Secured({"ADMIN"})
        @DeleteMapping(path = "/delete-user")
        public ResponseEntity<ResponseDto> deleteUser(
                @NotNull @RequestParam String username
        ){
            iAuthenticationService.deleteUser(username);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseDto("204", "User " + username + " deleted successfully"));
        }

        @Secured({"ADMIN"})
        @PostMapping(path = "/add-user")
        public ResponseEntity<ResponseDto> register(
                @Valid @NotNull @RequestBody RegisterRequest request
                ){
            iAuthenticationService.register(request);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "User created successfully"));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse> login(
                @Valid @NotNull @RequestBody LoginRequest request
        ){
            return ResponseEntity.status(HttpStatus.OK).body(
                    iAuthenticationService.authenticate(request)
            );
        }
}
