package com.example.demo.domain.mission.repository;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.global.enums.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 특정 지역의 도전 가능한 미션 조회
     * - 마감 기한이 지나지 않은 미션
     * - 아직 완료하지 않은 미션
     * - 마감 임박 순 정렬
     */
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "LEFT JOIN m.memberMissionList mm " +
            "WHERE s.regionId = :region " +
            "AND m.deadline > :now " +
            "AND (mm.id IS NULL OR " +
            "     (mm.member.id = :memberId AND mm.isComplete = false)) " +
            "ORDER BY m.deadline ASC")
    Page<Mission> findAvailableMissionsByRegion(
            @Param("region") Region region,
            @Param("memberId") Long memberId,
            @Param("now") LocalDate now,
            Pageable pageable
    );
}