package org.example.yulion.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return ApiErrorResponse.toResponseEntity(HttpStatus.NOT_ACCEPTABLE, ex, request);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        return ApiErrorResponse.toResponseEntity(HttpStatus.NOT_ACCEPTABLE, ex, request);
    }

    @ExceptionHandler(BadCredentialsException.class) // 비밀번호가 틀린 경우
    public ResponseEntity<ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        return ApiErrorResponse.toResponseEntity(HttpStatus.NOT_ACCEPTABLE, ex, request);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ApiErrorResponse> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex, HttpServletRequest request) {
        return ApiErrorResponse.toResponseEntity(HttpStatus.NOT_ACCEPTABLE, ex, request);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpClientErrorException(InsufficientAuthenticationException ex, HttpServletRequest request) {
        return ApiErrorResponse.toResponseEntity(HttpStatus.FORBIDDEN, ex, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return ApiErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST, ex.getBindingResult().getAllErrors().toString(), request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return ApiErrorResponse.toResponseEntity(HttpStatus.BAD_REQUEST, ex, request);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        log.error("GlobalExceptionHandler에 정의되지 않은 에러: " + ex.toString());
        return ApiErrorResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

}
