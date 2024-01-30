package org.example.yulion.domain.post.dto.response;

import org.example.yulion.domain.post.domain.Post;

public record PostDetailResponse (
        Long id,
        Long userId,
        String part,
        String category,
        String title,
        String content,
        String members) {
    public static PostDetailResponse from(final Post post) {
        return new PostDetailResponse(
                post.getId(),
                post.getWriter().getId(),
                post.getPart().getName(),
                post.getCategory().getName(),
                post.getTitle(),
                post.getContent(),
                post.getMembers()
        );

    }
}
