package org.example.yulion.domain.post.dto.response;

import java.util.List;

public record PostCommonListResponse(
        List<PostCommonSummaryResponse> content,
        int currentPage,
        int totalPage
) {
    public static PostCommonListResponse from(
            List<PostCommonSummaryResponse> content,
            int currentPage,
            int totalPage) {
        return new PostCommonListResponse(content, currentPage, totalPage);
    }
}
