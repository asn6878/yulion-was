package org.example.yulion.global.config.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.yulion.global.filter.AuthenticationCheckFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class SecurityCustomConfigurer implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private final DaoAuthenticationProvider authenticationProvider;
    private final AuthenticationCheckFilter authenticationCheckFilter;


    @Override
    public void init(HttpSecurity httpSecurity) {
    }

    @Override
    public void configure(HttpSecurity httpSecurity) {
        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.addFilterBefore(authenticationCheckFilter, UsernamePasswordAuthenticationFilter.class);
    }

}