package com.app.user.exceptions;

public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) {
        super(message);
    }
}