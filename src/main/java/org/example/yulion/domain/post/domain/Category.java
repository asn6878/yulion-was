package org.example.yulion.domain.post.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Category {
    NOTICE("공지사항"),
    COMMUNITY("커뮤니티"),
    STUDY("스터디"),
    TEAM_BUILDING("팀빌딩"),
    HOMEWORK("과제");

    private final String title;
}
