package org.example.yulion.domain.auth.dto.response;

import org.example.yulion.domain.user.domain.User;

public record SignupResult(
        long userId
) {
    public static SignupResult of(User user) {
        return new SignupResult(user.getId());
    }
}
