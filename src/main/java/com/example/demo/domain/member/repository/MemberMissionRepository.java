package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 진행중인 미션 조회 (마감 임박 순)
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.isComplete = false " +
            "ORDER BY m.deadline ASC")
    Page<MemberMission> findChallengingMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 완료한 미션 조회 (최근 완료 순)
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.isComplete = true " +
            "ORDER BY mm.completedAt DESC")
    Page<MemberMission> findCompletedMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 특정 지역에서 진행중인 미션 개수
    @Query("SELECT COUNT(mm) FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "JOIN m.store s " +
            "WHERE mm.member.id = :memberId " +
            "AND s.regionId = :region " +
            "AND mm.isComplete = false")
    Long countChallengingMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("region") com.example.demo.global.enums.Region region
    );
}