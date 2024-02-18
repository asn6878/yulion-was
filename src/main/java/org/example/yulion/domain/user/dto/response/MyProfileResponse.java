package org.example.yulion.domain.user.dto.response;

import org.example.yulion.domain.user.domain.User;

import java.time.LocalDate;

public record MyProfileResponse(
        long id,
        String phoneNumber,
        String nickname,
        String email,
        LocalDate birth,
        String profileImageUrl,
        String githubUsername
) {
    public static MyProfileResponse of(User user) {
        return new MyProfileResponse(
                user.getId(),
                user.getPhoneNumber(),
                user.getNickname(),
                user.getEmail(),
                user.getBirth(),
                user.getProfileImageUrl(),
                user.getGithubUsername()
        );
    }

}
