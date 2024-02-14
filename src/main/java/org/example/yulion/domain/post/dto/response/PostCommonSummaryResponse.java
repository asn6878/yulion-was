package org.example.yulion.domain.post.dto.response;

import org.example.yulion.domain.post.domain.Post;
import org.example.yulion.domain.user.domain.User;

import java.time.LocalDateTime;

public record PostCommonSummaryResponse(
    Long id,
    String part,
    String title,
    User writer,
    LocalDateTime createAt,
    Long viewCnt) {

    public static PostCommonSummaryResponse from(Post post) {
        return new PostCommonSummaryResponse(
            post.getId(),
            post.getPart().getName(),
            post.getTitle(),
            post.getWriter(),
            post.getCreateAt(),
            post.getViewCnt()
        );
    }
}
