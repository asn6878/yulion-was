package org.example.yulion.domain.user.domain;

public record EmailMessage(
        String toEmail,
        String subject,
        String message
) {
}