package com.github.wisaitas.auth_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.wisaitas.auth_service.entities.UserEntity;
import com.github.wisaitas.sharelib.repositories.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

}
