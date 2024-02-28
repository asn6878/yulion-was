package org.example.yulion.domain.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.yulion.domain.post.domain.Part;
import org.example.yulion.domain.post.domain.Post;
import org.example.yulion.domain.user.domain.User;

import static org.example.yulion.global.utils.ProjectTimeFormat.LOCAL_DATE_TIME_PATTERN;
import static org.example.yulion.global.utils.ProjectTimeFormat.LOCAL_DATE_TIME_PATTERN_EXAMPLE;


import java.time.LocalDateTime;

public record PostCommonSummaryResponse(
        Long id,
        Part part,
        String title,
        String writer,
        @Schema(description = "작성일", example = LOCAL_DATE_TIME_PATTERN_EXAMPLE)
    @JsonFormat(pattern = LOCAL_DATE_TIME_PATTERN)
    LocalDateTime createAt,
        Long viewCnt) {

    public static PostCommonSummaryResponse from(Post post) {
        return new PostCommonSummaryResponse(
            post.getId(),
            post.getPart(),
            post.getTitle(),
            post.getWriter().getNickname(),
            post.getCreateAt(),
            post.getViewCnt()
        );
    }
}
