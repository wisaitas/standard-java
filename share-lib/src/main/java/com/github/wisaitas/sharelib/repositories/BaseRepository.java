package com.github.wisaitas.sharelib.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    List<T> findAllWithCondition(Object condition, String... relations);

    Page<T> findAllWithCondition(Object condition, Pageable pageable, String... relations);

    Optional<T> findByCondition(Object condition, String... relations);

    T create(T item);

    List<T> createMany(List<T> items);

    T update(T item);

    List<T> updateMany(List<T> items);

    T save(T item);

    List<T> saveMany(List<T> items);

    void delete(T item);

    void deleteMany(List<T> items);

    BaseRepository<T, ID> withTransaction();
}
