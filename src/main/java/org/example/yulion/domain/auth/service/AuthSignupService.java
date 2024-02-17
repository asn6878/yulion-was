package org.example.yulion.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.yulion.domain.auth.dto.request.SignupRequest;
import org.example.yulion.domain.auth.dto.response.SignupResult;
import org.example.yulion.domain.user.domain.User;
import org.example.yulion.domain.user.domain.UserRole;
import org.example.yulion.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthSignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupResult signup(final SignupRequest signupRequest) {

        final User user = User.builder()
                .email(signupRequest.email())
                .nickname(signupRequest.nickname())
                .role(UserRole.USER)
                .password(encodePassword(signupRequest))
                .phoneNumber(signupRequest.phoneNumber())
                .gender(signupRequest.gender())
                .birth(signupRequest.birth())
                .profileImageUrl(signupRequest.profileImageUrl())
                .studentId(signupRequest.studentId())
                .githubUsername(signupRequest.githubUsername())
                .build();
        userRepository.save(user);

        log.info("회원가입 완료: ID: {}, 이메일: {}, 닉네임: {}", user.getId(), user.getEmail(), user.getNickname());

        return SignupResult.of(user);
    }


    private String encodePassword(final SignupRequest signupRequest) {
        return passwordEncoder.encode(signupRequest.password());
    }

}
