package org.example.yulion.domain.auth.dto.response;

import org.example.yulion.global.auth.AuthConstants;

public record TokenDto(
        String accessToken,
        String refreshToken,
        String type
) {
    public TokenDto(String accessToken, String refreshToken) {
        this(accessToken, refreshToken, AuthConstants.TOKEN_TYPE);
    }
}
