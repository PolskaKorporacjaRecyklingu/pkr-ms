package com.recykling.report.security.config;

import org.springframework.stereotype.Component;

@Component
public class SecurityConstant {
    //  15 minutes
    public final long TOKEN_DURATION = 900000;
    public final String SECRET_KEY = "d977816996de2deaf91666d3d943690b1265d33f054f5bf6ce149ac7e69c5f26";

}
