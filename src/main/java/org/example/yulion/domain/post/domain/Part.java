package org.example.yulion.domain.post.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Part {
    BE("백엔드"),
    FE("프론트엔드"),
    UIUX("UI/UX"),
    ALL("공통");

    private final String title;

}
