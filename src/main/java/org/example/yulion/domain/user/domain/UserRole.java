package org.example.yulion.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserRole {

    USER("ROLE_USER", "유저"),
    OPERATOR("ROLE_OPERATOR", "운영진"),
    ADMIN("ROLE_ADMIN", "관리자"),
    ;

    private final String key;
    private final String title;

    public static GrantedAuthority convertToAuthorities(UserRole role) {
        return new SimpleGrantedAuthority(role.getKey());
    }

}
