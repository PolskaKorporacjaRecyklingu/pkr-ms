package com.recykling.report.security.controller.response;

import java.util.Date;

public record AuthenticationResponse(String token, Date expirationDate) {
}
