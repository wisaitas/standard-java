package com.github.wisaitas.auth_service.entities;

import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import com.github.wisaitas.auth_service.enums.Gender;
import com.github.wisaitas.sharelib.entities.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@SQLRestriction("deleted_at IS NULL")
public class UserEntity extends BaseEntity {
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
