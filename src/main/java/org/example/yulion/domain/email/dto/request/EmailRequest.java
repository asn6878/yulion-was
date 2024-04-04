package org.example.yulion.domain.email.dto.request;

public record EmailRequest(
        String address,
        String category,
        String content
) {
}
