package org.example.yulion.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

import static org.example.yulion.global.utils.ProjectTimeFormat.LOCAL_DATE_PATTERN;
import static org.example.yulion.global.utils.ProjectTimeFormat.LOCAL_DATE_PATTERN_EXAMPLE;


@Schema(description = "사용자 프로필 수정 요청")
public record UserProfileUpdateRequest(
        String nickname,
        @Schema(description = "생년월일", example = LOCAL_DATE_PATTERN_EXAMPLE)
        @JsonFormat(pattern = LOCAL_DATE_PATTERN)
        LocalDate birth,
        String profileImageUrl,
        String githubUsername
) {
}
