package com.github.wisaitas.auth_service.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class ResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingFilter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(request, responseWrapper);
        } finally {
            // Log response
            String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
            if (!responseBody.isEmpty()) {
                try {
                    // พยายามแปลง response body เป็น JSON object
                    Object json = objectMapper.readValue(responseBody, Object.class);
                    logger.info("Response: {}", objectMapper.writeValueAsString(json));
                } catch (Exception e) {
                    // ถ้าไม่ใช่ JSON valid, log เป็น string ปกติ
                    logger.info("Response (non-JSON): {}", responseBody);
                }
            }

            // ส่ง content กลับไปให้ client
            responseWrapper.copyBodyToResponse();
        }
    }
}