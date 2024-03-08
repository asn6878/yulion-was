package org.example.yulion.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.yulion.global.config.response.ApiResponse;
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

/**
 * 커스텀되는 예외사항들을 처리하는 ExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 각 도메인 패키지별로 선언한 Exception들을 처리해주는 메소드
     * baseException을 받아와 처리한다.
     */
    @ExceptionHandler(BaseException.class)
    public ApiResponse<ApiExceptionResponse> handleBaseException(final BaseException ex){
        final BaseExceptionType baseExceptionType = ex.exceptionType();
        final ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                baseExceptionType.errorCode(), baseExceptionType.errorMessage()
        );
        log.info("error = {}", apiExceptionResponse);

        return ApiResponse.createError(apiExceptionResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ApiResponse<ApiExceptionResponse> handleBadCredentialsException(final BadCredentialsException ex){
        final ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                401,
                ex.toString()
        );

        return ApiResponse.createError(apiExceptionResponse);
    }

}
