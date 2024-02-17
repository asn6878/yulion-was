package org.example.yulion.domain.post.dto.response;

import java.util.List;

public record PostEducationListResponse(
        List<PostEducationSummaryResponse> content,
        int currentPage,
        int totalPage
) {
    public static PostEducationListResponse from(
            List<PostEducationSummaryResponse> content,
            int currentPage,
            int totalPage) {
        return new PostEducationListResponse(content, currentPage, totalPage);
    }
}
