package com.github.wisaitas.auth_service.services.impls.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.github.wisaitas.auth_service.entities.UserEntity;

public class UserSpecification {
    public static Specification<UserEntity> fullnameContains(String fullname) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("fullname"), "%" + fullname + "%");
    }
}
