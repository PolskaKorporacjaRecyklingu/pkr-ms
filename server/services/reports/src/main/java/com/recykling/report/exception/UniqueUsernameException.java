package com.recykling.report.exception;

public class UniqueUsernameException extends RuntimeException{

    public UniqueUsernameException(String username) {
        super("User with username: " + username + " already exist");
    }
}
