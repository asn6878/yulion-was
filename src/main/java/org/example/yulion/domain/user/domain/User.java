package org.example.yulion.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.yulion.domain.common.BaseTimeEntity;
import org.example.yulion.domain.user.dto.request.UserProfileUpdateRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Objects.requireNonNullElse;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"password"})
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Column(unique = true, nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate birth;
    private String gender;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(unique = true, nullable = false)
    private Long studentId;

    @Column(unique = true)
    private String githubUsername;

    @Column(nullable = false)
    private String profileImageUrl;

    private LocalDateTime deletedAt;

    @Builder
    protected User(String password, String phoneNumber, String nickname, String email, UserRole role, String gender,
                   LocalDate birth, Long studentId, String githubUsername, String profileImageUrl) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
        this.gender = gender;
        this.birth = birth;
        this.studentId = studentId;
        this.githubUsername = githubUsername;
        this.profileImageUrl = profileImageUrl;
        this.status = UserStatus.WAITING;
    }

    public void updateProfile(UserProfileUpdateRequest updateRequest) {
        this.nickname = requireNonNullElse(updateRequest.nickname(), this.nickname);
        this.birth = requireNonNullElse(updateRequest.birth(), this.birth);
        this.githubUsername = requireNonNullElse(updateRequest.githubUsername(), this.githubUsername);
        this.profileImageUrl = requireNonNullElse(updateRequest.profileImageUrl(), this.profileImageUrl);
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
    }

    public void withdraw() {
        this.status = UserStatus.SUSPENDED;
        this.deletedAt = LocalDateTime.now();
    }

}
