package org.example.yulion.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.auth.dto.request.LoginRequest;
import org.example.yulion.domain.auth.dto.request.SignupRequest;
import org.example.yulion.domain.auth.dto.response.SignupResult;
import org.example.yulion.domain.auth.dto.response.TokenDto;
import org.example.yulion.domain.auth.service.AuthSignupService;
import org.example.yulion.domain.auth.service.AuthTokenService;
import org.example.yulion.global.config.swagger.DisableSwaggerSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthSignupService authSignupService;
    private final AuthTokenService authTokenService;

    @DisableSwaggerSecurity
    @PostMapping("/signup")
    public ResponseEntity<SignupResult> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<>(authSignupService.signup(signupRequest), HttpStatus.CREATED);
    }

    @DisableSwaggerSecurity
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authTokenService.login(loginRequest));
    }

    @DisableSwaggerSecurity
    @PostMapping("/token")
    public ResponseEntity<TokenDto> refreshToken(
            String refreshToken
    ) {
        return ResponseEntity.ok(authTokenService.refreshTokens(refreshToken));
    }

}
