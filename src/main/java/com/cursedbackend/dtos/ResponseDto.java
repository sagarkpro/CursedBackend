package com.cursedbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    boolean success;
    T data;
    ErrorDto error;

    public static <T> ResponseDto<T> successDto() {
        return ResponseDto.<T>builder().success(true).build();
    }

    public static <T> ResponseDto<T> errorDto() {
        return ResponseDto.<T>builder().success(false).build();
    }

    public static <T> ResponseDto<T> errorDto(String message) {
        return ResponseDto.<T>builder()
                .success(false)
                .error(ErrorDto.builder()
                        .message(message)
                        .build())
                .build();
    }

    public static <T> ResponseDto<T> errorDto(String message, String details) {
        return ResponseDto.<T>builder()
                .success(false)
                .error(ErrorDto.builder()
                        .message(message)
                        .details(details)
                        .build())
                .build();
    }
}
