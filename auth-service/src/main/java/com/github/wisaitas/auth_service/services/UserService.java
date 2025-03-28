package com.github.wisaitas.auth_service.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.wisaitas.auth_service.dtos.queries.UserQuery;
import com.github.wisaitas.auth_service.entities.UserEntity;
import com.github.wisaitas.auth_service.repositories.UserRepository;

public interface UserService {
    Page<UserEntity> getAllUsers(UserQuery query);
}
