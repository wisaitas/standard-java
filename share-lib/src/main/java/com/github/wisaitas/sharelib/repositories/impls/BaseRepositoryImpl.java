package com.github.wisaitas.sharelib.repositories.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.github.wisaitas.sharelib.repositories.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> domainClass;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.domainClass = entityInformation.getJavaType();
    }

    @Override
    public List<T> findAllWithCondition(Object condition, String... relations) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        Root<T> root = query.from(domainClass);
        

        // นี่เป็นเพียงตัวอย่าง
        
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Page<T> findAllWithCondition(Object condition, Pageable pageable, String... relations) {
        // ทำการ implement โค้ดสำหรับการค้นหาแบบแบ่งหน้า
        return findAll(pageable);
    }

    @Override
    public Optional<T> findByCondition(Object condition, String... relations) {
        // ทำการ implement โค้ดสำหรับการค้นหาด้วยเงื่อนไข
        return Optional.empty();
    }

    @Override
    @Transactional
    public T create(T item) {
        return save(item);
    }

    @Override
    @Transactional
    public List<T> createMany(List<T> items) {
        return saveAll(items);
    }

    @Override
    @Transactional
    public T update(T item) {
        return save(item);
    }

    @Override
    @Transactional
    public List<T> updateMany(List<T> items) {
        return saveAll(items);
    }

    @Override
    @Transactional
    public T save(T item) {
        return super.save(item);
    }

    @Override
    @Transactional
    public List<T> saveMany(List<T> items) {
        return saveAll(items);
    }

    @Override
    @Transactional
    public void delete(T item) {
        super.delete(item);
    }

    @Override
    @Transactional
    public void deleteMany(List<T> items) {
        deleteAll(items);
    }

    @Override
    public BaseRepository<T, ID> withTransaction() {
        // สร้าง Transaction
        return this;
    }
}