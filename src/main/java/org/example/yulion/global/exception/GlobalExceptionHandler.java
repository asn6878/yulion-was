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

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ApiResponse<ApiExceptionResponse> handleBaseException(final BaseException ex){
        final BaseExceptionType baseExceptionType = ex.exceptionType();
        final ApiExceptionResponse apiErrorResponse = new ApiExceptionResponse(
                baseExceptionType.errorCode(), baseExceptionType.errorMessage()
        );

        log.info("error = {}", apiErrorResponse);

        return ApiResponse.createError(apiErrorResponse);

    }

}
