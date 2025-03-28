package com.github.wisaitas.auth_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.wisaitas.auth_service.dtos.queries.UserQuery;
import com.github.wisaitas.auth_service.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) UserQuery query) {
        return ResponseEntity.ok(userService.getAllUsers(query));
    }
}
