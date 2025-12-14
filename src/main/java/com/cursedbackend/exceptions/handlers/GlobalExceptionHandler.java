package com.cursedbackend.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cursedbackend.dtos.ErrorDto;
import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.exceptions.InvalidTokenException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResponseDto> handleInvalidTokenException(
            InvalidTokenException ex,
            WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseDto.builder()
                        .success(false)
                        .error(new ErrorDto("Invalid token", null))
                        .build());
    }

}
