package com.example.spring_boot_a.repository;

import com.example.spring_boot_a.domain.entity.mission.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findByStore_StoreId(Long storeId, Pageable pageable);
}
