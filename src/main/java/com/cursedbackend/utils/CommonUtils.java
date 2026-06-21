package com.cursedbackend.utils;

import org.springframework.http.ResponseEntity;

import com.cursedbackend.domain.RequestContextHolder;
import com.cursedbackend.domain.UserInfo;
import com.cursedbackend.dtos.ResponseDto;

public class CommonUtils {
    private CommonUtils() {
    }

    public static <T> ResponseEntity<ResponseDto<T>> handleResponse(ResponseDto<T> response) {
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        if (response.getError() != null && response.getError().getStatus() != null) {
            return ResponseEntity.status(response.getError().getStatus()).body(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    public static String getCurrentUserEmail() {
        var currentUser = RequestContextHolder.get().userInfo();
        if (currentUser != null)
            return currentUser.email();
        return null;
    }

    public static UserInfo getCurrentUser() {
        return RequestContextHolder.get().userInfo();
    }
}
