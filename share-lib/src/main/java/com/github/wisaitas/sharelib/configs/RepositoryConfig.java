package com.github.wisaitas.sharelib.configs;

import com.github.wisaitas.sharelib.repositories.impls.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.wisaitas.sharelib.repositories", repositoryBaseClass = BaseRepositoryImpl.class)
public class RepositoryConfig {
    // คลาสนี้เป็นแค่ configuration ไม่จำเป็นต้องมี method เพิ่มเติม
}