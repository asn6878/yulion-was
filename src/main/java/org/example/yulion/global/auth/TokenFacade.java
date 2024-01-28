package org.example.yulion.global.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.yulion.domain.auth.dto.response.TokenDto;
import org.example.yulion.global.auth.jwt.JwtTokenProvider;
import org.example.yulion.global.auth.refreshtoken.RefreshToken;
import org.example.yulion.global.auth.refreshtoken.RefreshTokenService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class TokenFacade {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;


    public TokenDto generate(String memberId) {

        final String accessToken = jwtTokenProvider.issueJwtToken(memberId);
        final String refreshToken = refreshTokenService.issueRefreshToken(memberId);

        log.info("로그인, 토큰 신규 발급. Member ID: {}", memberId);

        return new TokenDto(accessToken, refreshToken);
    }

    /**
     * 기존의 유효한 Refresh 토큰으로 새로운 Access 토큰과 Refresh 토큰을 발급하는 함수
     *
     * @param requestRefreshToken - 토큰 재발급 정보
     * @return - 재발급된 토큰들 반환
     */
    public TokenDto refreshTokens(final String requestRefreshToken) {
        // Refresh 토큰 재발급
        final RefreshToken refreshToken = refreshTokenService.refresh(requestRefreshToken);

        final String memberId = refreshToken.getMemberId();

        // Access Token 재발급
        final String newAccessToken = jwtTokenProvider.issueJwtToken(memberId);

        log.info("JWT, 리프래시 토큰 재발급 완료. Member ID : {}", memberId);

        return new TokenDto(newAccessToken, refreshToken.getToken());
    }


}
