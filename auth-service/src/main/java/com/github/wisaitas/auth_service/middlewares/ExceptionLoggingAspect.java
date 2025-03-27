package com.github.wisaitas.auth_service.middlewares;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterReturning(pointcut = "execution(* com.github.wisaitas.auth_service.middlewares.GlobalExceptionHandler.*(..))", returning = "result")
    public void logExceptionResponse(JoinPoint joinPoint, Object result) {
        try {
            logger.info("Error Response: {}", objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            logger.error("Failed to log error response", e);
        }
    }
}