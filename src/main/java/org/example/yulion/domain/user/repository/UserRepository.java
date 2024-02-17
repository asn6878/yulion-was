package org.example.yulion.domain.user.repository;

import org.example.yulion.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    default User findOrThrow(Long userId) {
        return findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

}
