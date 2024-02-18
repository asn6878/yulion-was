package org.example.yulion.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.user.domain.User;
import org.example.yulion.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserStatusService {

    private final UserRepository userRepository;

    public void activateUser(final Long userId) {
        User user = userRepository.findOrThrow(userId);
        user.activate();
    }

    public void withdrawUser(final Long userId) {
        User user = userRepository.findOrThrow(userId);
        user.withdraw();
    }

}
