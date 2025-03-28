package com.github.wisaitas.auth_service.services.impls;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.wisaitas.auth_service.dtos.queries.UserQuery;
import com.github.wisaitas.auth_service.entities.UserEntity;
import com.github.wisaitas.auth_service.repositories.UserRepository;
import com.github.wisaitas.auth_service.services.UserService;
import com.github.wisaitas.auth_service.services.impls.specifications.UserSpecification;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserEntity> getAllUsers(UserQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        Specification<UserEntity> specification = Specification.where(null);
        if (query.getFullname() != null) {
            specification = specification.and(UserSpecification.fullnameContains(query.getFullname()));
        }
        return userRepository.findAll(specification, pageable);
    }
}