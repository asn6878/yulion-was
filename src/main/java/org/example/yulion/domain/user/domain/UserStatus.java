package org.example.yulion.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserStatus {

    WAITING("승인 대기"),
    ACTIVE("활동 중"),
    SUSPENDED("활동 정지"),
    ;

    private final String title;


}
