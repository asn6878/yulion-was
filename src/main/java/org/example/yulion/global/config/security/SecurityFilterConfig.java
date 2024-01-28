package org.example.yulion.global.config.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.yulion.global.auth.entrypoint.AuthEntryPointJwt;
import org.example.yulion.global.auth.jwt.JwtTokenProvider;
import org.example.yulion.global.auth.userdetails.UserDetailsServiceImpl;
import org.example.yulion.global.filter.AuthenticationCheckFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Configuration
class SecurityFilterConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public AuthenticationCheckFilter authenticationCheckFilter() {
        return new AuthenticationCheckFilter(userDetailsService, jwtTokenProvider, authEntryPointJwt);
    }

}
