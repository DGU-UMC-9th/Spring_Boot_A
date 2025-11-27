package com.example.spring_boot_a.repository;

import com.example.spring_boot_a.domain.entity.enums.UserMissionStatus;
import com.example.spring_boot_a.domain.entity.user.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Page<UserMission> findByUser_UserIdAndStatus(Long userId,
                                                 UserMissionStatus status,
                                                 Pageable pageable);
}
