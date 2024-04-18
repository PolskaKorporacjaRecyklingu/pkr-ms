package com.recykling.report.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "admin")
@Getter
@Setter
public class AdminAccountData {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
