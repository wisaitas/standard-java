package com.github.wisaitas.auth_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.wisaitas.sharelib.controllers.BaseController;
import com.github.wisaitas.sharelib.dtos.responses.SuccessResponse;

@RequestMapping("/users")
public class UserController extends BaseController {
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok("hello");
    }
}
