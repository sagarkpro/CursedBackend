package com.cursedbackend.exceptions.handlers;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.cursedbackend.dtos.ErrorDto;
import com.cursedbackend.dtos.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ResponseDto responseDto = ResponseDto.builder()
                .success(false)
                .error(new ErrorDto("Authentication required", null))
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
    }

}
