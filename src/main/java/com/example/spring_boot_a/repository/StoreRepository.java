package com.example.spring_boot_a.repository;

import com.example.spring_boot_a.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
