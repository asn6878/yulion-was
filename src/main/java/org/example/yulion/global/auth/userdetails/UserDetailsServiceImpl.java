package org.example.yulion.global.auth.userdetails;

import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.user.domain.User;
import org.example.yulion.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    /**
     * 로그인을 위해 아이디로 가입한 멤버 찾기
     *
     * @param identity - 구분을 위한 고유 정보. Member ID
     */
    @Override
    public UserDetails loadUserByUsername(final String identity) throws UsernameNotFoundException {
        try {
            final Long memberId = Long.parseLong(identity);
            return loadMemberById(memberId);
        } catch (NumberFormatException exception) {
            throw new UsernameNotFoundException(identity);
        }
    }


    private CustomUserDetails loadMemberById(final Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId.toString()));
        return CustomUserDetails.of(user);
    }

}
