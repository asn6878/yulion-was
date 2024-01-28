package org.example.yulion.global.resolver.accesstoken;

import java.time.Instant;

public record AccessToken(
        String accessToken,
        String subject,
        Instant expiryDate
) {
}
