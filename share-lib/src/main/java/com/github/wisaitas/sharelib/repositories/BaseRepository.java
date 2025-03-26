package com.github.wisaitas.sharelib.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.wisaitas.sharelib.dtos.queries.PaginationQuery;
import com.github.wisaitas.sharelib.entities.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {

    List<T> getAll(PaginationQuery pagination, Object condition);

    T getBy(Object condition);

    T create(T item);

    List<T> createMany(List<T> items);

    T update(T item);

    List<T> updateMany(List<T> items);

    T saveEntity(T item);

    List<T> saveMany(List<T> items);

    void delete(T item);

    void deleteMany(List<T> items);

    @Transactional
    BaseRepository<T> withTransaction();
}