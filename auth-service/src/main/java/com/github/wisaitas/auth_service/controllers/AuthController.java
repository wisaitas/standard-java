package com.github.wisaitas.auth_service.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.wisaitas.auth_service.dtos.requests.auth.RegisterRequest;
import com.github.wisaitas.sharelib.dtos.responses.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) throws BadRequestException {
        throw new BadRequestException("test");
        // return
        // ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.builder()
        // .message("user registered successfully")
        // .build());
    }
}
