package com.github.wisaitas.auth_service.middlewares;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.web.bind.annotation.ControllerAdvice)")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        // Log request
        Map<String, Object> requestLog = new HashMap<>();
        requestLog.put("method", request.getMethod());
        requestLog.put("uri", request.getRequestURI());
        requestLog.put("params", request.getParameterMap());

        // Log request body if exists
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] != null) {
            // หลีกเลี่ยงการ log exception objects
            if (!(args[0] instanceof Exception)) {
                requestLog.put("body", args[0]);
            }
        }

        logger.info("Request: {}", objectMapper.writeValueAsString(requestLog));

        try {
            // Proceed with the method execution
            Object result = joinPoint.proceed();

            // Log successful response
            logger.info("Response: {}", objectMapper.writeValueAsString(result));

            return result;
        } catch (Exception e) {
            // นำข้อมูล error response มาสร้างเป็น JSON
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("exception", e.getClass().getName());
            errorResponse.put("message", e.getMessage());

            // Log error response
            logger.info("Error Response: {}", objectMapper.writeValueAsString(errorResponse));

            // โยน exception ต่อไปให้ ExceptionHandler จัดการ
            throw e;
        }
    }
}