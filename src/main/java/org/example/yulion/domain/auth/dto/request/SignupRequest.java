package org.example.yulion.domain.auth.dto.request;

import jakarta.validation.constraints.*;
import org.example.yulion.global.auth.AuthConstants;

import java.time.LocalDate;

public record SignupRequest(

        @NotBlank(message = "전화번호는 공백이 아니어야 합니다")
        @Pattern(message = "전화번호는 숫자만 허용되며, '-'을 포함한 형식입니다", regexp = AuthConstants.phoneNumberRegexp)
        String phoneNumber,

        @NotBlank(message = "닉네임은 공백이 아니어야 합니다")
        String nickname,

        @NotBlank(message = "이메일은 공백이 아니어야 합니다")
        @Email(message = "이메일 주소에 @ 기호와 도메인 명이 필요합니다")
        String email,

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다")
        @Pattern(message = "비밀번호는 숫자, 영어, 특수문자를 포함해야 합니다", regexp = AuthConstants.passwordRegexp)
        @Size(min = 6, message = "비밀번호의 길이는 최소 6자 입니다")
        String password,

        @NotBlank(message = "성별은 공백이 아니어야 합니다")
        String gender,

        LocalDate birth,

        @NotBlank(message = "프로필 이미지 URL은 공백이 아니어야 합니다")
        String profileImageUrl,

        @NotNull(message = "학번은 null이 아니어야 합니다")
        Long studentId,
        String githubUsername
) {
}
