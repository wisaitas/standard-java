package com.github.wisaitas.sharelib.configs;

import com.github.wisaitas.sharelib.repositories.impls.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.wisaitas", repositoryBaseClass = BaseRepositoryImpl.class)
public class RepositoryConfig {
}