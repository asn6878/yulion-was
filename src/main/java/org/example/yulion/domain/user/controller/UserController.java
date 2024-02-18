package org.example.yulion.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.user.dto.request.UserProfileUpdateRequest;
import org.example.yulion.domain.user.dto.response.MyProfileResponse;
import org.example.yulion.domain.user.service.UserProfileService;
import org.example.yulion.global.auth.userdetails.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserProfileService userProfileService;


    @Operation(summary = "자신의 프로필 조회")
    @GetMapping("/me/profiles")
    public ResponseEntity<MyProfileResponse> getMyProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(userProfileService.getMyProfile(userDetails.getId()));
    }

    @Operation(summary = "자신의 프로필 수정")
    @PutMapping("/me/profiles")
    public ResponseEntity<MyProfileResponse> updateMyProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UserProfileUpdateRequest updateRequest) {
        return ResponseEntity.ok(userProfileService.updateProfile(userDetails.getId(), updateRequest));
    }


}
