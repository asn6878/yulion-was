package org.example.yulion.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "게시글 목록 조회 (스터디, 팀빌딩, 과제)")
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
