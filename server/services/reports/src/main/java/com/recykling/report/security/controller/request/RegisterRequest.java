package com.recykling.report.security.controller.request;

import com.recykling.report.security.user.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RegisterRequest {
    @Pattern(regexp = "^[a-zA-Z]+$", message = "FirstName must contain only letters")
    @NotNull(message = "FirstName cannot be empty")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "LastName must contain only letters")
    @NotNull(message = "LastName cannot be empty")
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only letters")
    @NotNull(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Role cannot be empty")
    private Role role;
}
