package org.example.yulion.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.yulion.global.auth.refreshtoken.RefreshToken;
import org.example.yulion.global.auth.refreshtoken.RefreshTokenService;
import org.example.yulion.global.resolver.accesstoken.AccessToken;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthLogoutService {

    private final RefreshTokenService refreshTokenService;

    public void logout(final AccessToken accessToken, final String logoutRefreshToken) {

        final RefreshToken refreshToken = refreshTokenService.findOrThrow(logoutRefreshToken);
        final String currentSubject = accessToken.subject();
        final String tokenSubject = refreshToken.getMemberId();

        checkTokenOwner(currentSubject, tokenSubject);

        refreshTokenService.deleteToken(refreshToken);
        log.info("로그아웃: {}", currentSubject);
    }


    private void checkTokenOwner(final String currentSubject, final String tokenSubject) {
        if (!currentSubject.equals(tokenSubject)) {
            final var errorMessage = String.format("비정상적인 로그아웃 시도! 현재 멤버 ID: %s, But tried ID: %s", currentSubject, tokenSubject);
            log.warn(errorMessage);
            throw new RequestRejectedException(errorMessage);
        }
    }

}
