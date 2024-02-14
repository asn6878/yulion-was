package org.example.yulion.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.yulion.domain.common.BaseTimeEntity;
import org.example.yulion.domain.post.domain.Post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"password"})
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    @Column(unique = true, nullable = false)
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

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Builder
    protected User(String password, String phoneNumber, String nickname, String email, UserRole role, String gender, LocalDate birth) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
        this.gender = gender;
        this.birth = birth;
        this.status = UserStatus.WAITING;
    }

}
