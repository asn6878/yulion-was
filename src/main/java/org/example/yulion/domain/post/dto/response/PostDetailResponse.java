package org.example.yulion.domain.post.dto.response;

import org.example.yulion.domain.post.domain.Post;
import org.example.yulion.domain.post.domain.Part;
import org.example.yulion.domain.post.domain.Category;

public record PostDetailResponse (
        Long id,
        Long userId,
        Part part,
        Category category,
        String title,
        String content,
        String members) {
    public static PostDetailResponse from(final Post post) {
        return new PostDetailResponse(
                post.getId(),
                post.getWriter().getId(),
                post.getPart(),
                post.getCategory(),
                post.getTitle(),
                post.getContent(),
                post.getMembers()
        );
    }
}
