package com.example.spring_boot_a.domain.repository;

import com.example.spring_boot_a.domain.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query("""
      select um from UserMission um
      where um.user.userId = :userId
        and um.isComplete = true
        and not exists (
          select 1 from Review r
          where r.user.userId = um.user.userId
            and r.store.storeId = um.mission.store.storeId
        )
      order by um.userMissionId desc
    """)
    List<UserMission> findCompletesWithoutReview(@Param("userId") Long userId);
}
