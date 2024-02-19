package org.example.yulion.domain.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "게시글 목록 조회 (공지사항, 커뮤니티)")
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
