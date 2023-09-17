package com.ecommerce.kgateway.security;

import com.ecommerce.kgateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtil;

    private final SecurityContext securityContext;

    @Autowired
    public JwtAuthenticationFilter(JwtUtils jwtUtil, SecurityContext securityContext) {
        this.jwtUtil = jwtUtil;
        this.securityContext = securityContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();
        filterChain.doFilter(request, response);
    }
}
