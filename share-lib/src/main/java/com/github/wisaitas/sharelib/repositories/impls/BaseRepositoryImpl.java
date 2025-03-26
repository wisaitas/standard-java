package com.github.wisaitas.sharelib.repositories.impls;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.github.wisaitas.sharelib.dtos.queries.PaginationQuery;
import com.github.wisaitas.sharelib.entities.BaseEntity;
import com.github.wisaitas.sharelib.repositories.BaseRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.function.Function;

public class BaseRepositoryImpl<T extends BaseEntity> extends SimpleJpaRepository<T, UUID>
        implements BaseRepository<T> {

    private final EntityManager entityManager;
    private final Class<T> domainClass;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.domainClass = entityInformation.getJavaType();
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.domainClass = domainClass;
    }

    @Override
    public List<T> getAll(PaginationQuery pagination, Object condition) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(domainClass);
        Root<T> root = query.from(domainClass);

        if (pagination.getPage() != null && pagination.getPageSize() != null) {
            Sort sort = Sort.unsorted();
            if (pagination.getSort() != null && pagination.getOrder() != null) {
                sort = Sort.by(pagination.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                        pagination.getSort());
            }

            PageRequest pageRequest = PageRequest.of(
                    pagination.getPage(), pagination.getPageSize(), sort);

            return findAll(pageRequest).getContent();
        }

        return findAll();
    }

    @Override
    public T getBy(Object condition) {
        return findAll().stream().findFirst().orElse(null);
    }

    @Override
    public T create(T item) {
        return super.save(item);
    }

    @Override
    public List<T> createMany(List<T> items) {
        return saveAll(items);
    }

    @Override
    public T update(T item) {
        return super.save(item);
    }

    @Override
    public List<T> updateMany(List<T> items) {
        return saveAll(items);
    }

    @Override
    public T saveEntity(T item) {
        return super.save(item);
    }

    @Override
    public List<T> saveMany(List<T> items) {
        return saveAll(items);
    }

    @Override
    public void delete(T item) {
        super.delete(item);
    }

    @Override
    public void deleteMany(List<T> items) {
        deleteAll(items);
    }

    @Override
    @Transactional
    public BaseRepository<T> withTransaction() {
        return this;
    }
}