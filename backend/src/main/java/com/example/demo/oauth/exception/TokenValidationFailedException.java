package com.example.demo.oauth.exception;

public class TokenValidationFailedException extends RuntimeException {

    public TokenValidationFailedException() {

        super("Failed to generate Token.");
    }

    private TokenValidationFailedException(String message) {

        super(message);
    }

}
