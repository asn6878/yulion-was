package org.example.yulion.global.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthErrorException extends AuthenticationException {

    private final String message;

    public AuthErrorException(String message) {
        super(message, null);
        this.message = message;
    }

    @Override
    public String toString() {
        return "AUTH 사유: " + message;
    }
}
