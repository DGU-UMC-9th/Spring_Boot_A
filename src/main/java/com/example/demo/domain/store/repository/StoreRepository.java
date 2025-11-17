package com.example.demo.domain.store.repository;

import com.example.demo.domain.store.entity.Store;
import com.example.demo.global.enums.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    // 지역별 가게 조회 (페이징)
    Page<Store> findByRegionId(Region region, Pageable pageable);
}