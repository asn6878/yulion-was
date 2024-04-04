package org.example.yulion.domain.email.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.yulion.domain.email.dto.request.EmailRequest;
import org.example.yulion.domain.email.service.EmailService;
import org.example.yulion.global.config.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Email")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmailService emailService;
    @GetMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
        emailService.sendEmail(request);

        return ResponseEntity.ok(ApiResponse.createSuccess("메일 전송 완료"));
    }
}
