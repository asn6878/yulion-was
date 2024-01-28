package org.example.yulion.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiErrorResponse {
    private final String path;
    private final String error;
    private final int statusCode;
    private final String message;

    public static ResponseEntity<ApiErrorResponse> toResponseEntity(
            @NotNull HttpStatus httpStatus,
            Exception exception,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(httpStatus)
                .body(of(httpStatus, exception.getMessage(), request));
    }

    public static ResponseEntity<ApiErrorResponse> toResponseEntity(
            @NotNull HttpStatus httpStatus,
            String message,
            HttpServletRequest request
    ) {
        return ResponseEntity
                .status(httpStatus)
                .body(of(httpStatus, message, request));
    }

    private static ApiErrorResponse of(
            @NotNull HttpStatus httpStatus,
            String message,
            HttpServletRequest request
    ) {
        return ApiErrorResponse.builder()
                .path(request.getServletPath())
                .statusCode(httpStatus.value())
                .error(httpStatus.name())
                .message(message)
                .build();
    }

    @Builder
    private ApiErrorResponse(String path, String error, int statusCode, String message) {
        this.path = path;
        this.error = error;
        this.statusCode = statusCode;
        this.message = message;
    }
}
