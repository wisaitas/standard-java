package com.github.wisaitas.auth_service.repositories;

import org.springframework.stereotype.Repository;

import com.github.wisaitas.auth_service.entities.UserEntity;
import com.github.wisaitas.sharelib.repositories.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

}
