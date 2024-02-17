package org.example.yulion.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.user.service.UserStatusService;
import org.example.yulion.global.auth.userdetails.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users/status")
public class UserStatusController {

    private final UserStatusService userStatusService;

    @Operation(summary = "계정 활성화")
    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Void> activateUser(@RequestParam("user_id") Long userId) {
        userStatusService.activateUser(userId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "자신의 계정 탈퇴")
    @DeleteMapping("/withdraw")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userStatusService.withdrawUser(userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}
