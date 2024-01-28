package org.example.yulion.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.auth.service.AuthLogoutService;
import org.example.yulion.global.auth.AuthConstants;
import org.example.yulion.global.resolver.accesstoken.AccessToken;
import org.example.yulion.global.resolver.accesstoken.AccessTokenInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class LogoutController {

    private final AuthLogoutService authLogoutService;


    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @CookieValue(AuthConstants.REFRESH_TOKEN) String refreshToken,
            @AccessTokenInfo AccessToken accessToken
    ) {
        authLogoutService.logout(accessToken, refreshToken);
        return ResponseEntity.noContent().build();
    }

}
