package com.github.wisaitas.auth_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok("hello");
    }
}
