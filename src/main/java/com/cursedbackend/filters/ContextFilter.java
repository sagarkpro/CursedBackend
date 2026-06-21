package com.cursedbackend.filters;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cursedbackend.domain.RequestContext;
import com.cursedbackend.domain.RequestContextHolder;
import com.cursedbackend.domain.UserInfo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                var jwtAuthToken = (JwtAuthenticationToken) auth;
                Map<String, Object> claims = jwtAuthToken.getToken().getClaims();
                var ctx = new RequestContext(UserInfo.from(claims));
                RequestContextHolder.set(ctx);
            }
            filterChain.doFilter(request, response);
        } finally {
            RequestContextHolder.clear();
        }
    }

}
