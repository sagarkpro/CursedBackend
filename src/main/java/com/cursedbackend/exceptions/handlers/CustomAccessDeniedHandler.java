package com.cursedbackend.exceptions.handlers;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.cursedbackend.dtos.ErrorDto;
import com.cursedbackend.dtos.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ResponseDto responseDto = ResponseDto.builder()
                .success(false)
                .error(new ErrorDto("You are not authorized to access this resource", null))
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
    }

}
