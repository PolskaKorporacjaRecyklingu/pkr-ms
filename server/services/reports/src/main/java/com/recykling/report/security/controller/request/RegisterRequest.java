package com.recykling.report.security.controller.request;

import com.recykling.report.security.user.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "FirstName cannot be empty")
    private String firstName;

    @NotNull(message = "LastName cannot be empty")
    private String lastName;

    @NotNull(message = "Username cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Role cannot be empty")
    private Role role;
}
