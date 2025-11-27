package com.umc.training.domain.store.repository;

import org.springframework.data.repository.Repository;

import com.umc.training.domain.store.entity.Store;

import java.util.Optional;

public interface StoreRepository extends Repository<Store, Long> {

    boolean existsById(Long id);

    Optional<Store> findById(Long id);

    Store save(Store store);

}
