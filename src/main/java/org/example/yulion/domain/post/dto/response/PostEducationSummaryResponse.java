package org.example.yulion.domain.post.dto.response;

import org.example.yulion.domain.post.domain.Part;
import org.example.yulion.domain.post.domain.Post;

import java.time.LocalDateTime;

public record PostEducationSummaryResponse(
        Long id,
        Part part,
        String title,
        String members,
        String mentor) {

    public static PostEducationSummaryResponse from(Post post) {
        return new PostEducationSummaryResponse(
                post.getId(),
                post.getPart(),
                post.getTitle(),
                post.getMembers(),
                post.getMentor()
        );
    }
}
