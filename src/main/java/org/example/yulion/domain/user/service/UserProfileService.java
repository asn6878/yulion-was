package org.example.yulion.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.user.domain.User;
import org.example.yulion.domain.user.dto.request.UserProfileUpdateRequest;
import org.example.yulion.domain.user.dto.response.MyProfileResponse;
import org.example.yulion.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MyProfileResponse getMyProfile(Long userId) {
        return MyProfileResponse.of(userRepository.findOrThrow(userId));
    }

    @Transactional
    public MyProfileResponse updateProfile(Long userId, UserProfileUpdateRequest updateRequest) {
        User user = userRepository.findOrThrow(userId);
        user.updateProfile(updateRequest);
        return MyProfileResponse.of(user);
    }

}
