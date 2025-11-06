package com.umc.training.domain.mission.entity.repository;

import com.umc.training.domain.member.entity.Member;
import com.umc.training.domain.member.entity.MemberMission;
import com.umc.training.domain.member.entity.enums.MemberStatus;
import com.umc.training.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends Repository<Mission, Long> {


    // 미션 3
    @Query(
            value = "SELECT m " +
                    "FROM MemberMission m " +
                    "left join fetch Mission " +
                    "where m.member.id = :member " +
                    "AND m.status IN (" +
                    "com.umc.training.domain.mission.entity.enums.MissionStatus.IN_PROGRESS, " +
                    "com.umc.training.domain.mission.entity.enums.MissionStatus.IN_THE_WORKS" +
                    ") " +
                    "order by m.createdAt DESC",
            countQuery = "SELECT COUNT(m) " +
                    "FROM MemberMission m " +
                    "WHERE m.member = :member " +
                    "AND m.status IN (" +
                    "com.umc.training.domain.mission.entity.enums.MissionStatus.IN_PROGRESS, " +
                    "com.umc.training.domain.mission.entity.enums.MissionStatus.IN_THE_WORKS" +
                    ")"

    )
    Page<MemberMission> findMissionInProgressOrCompletedByMember(
            @Param("member") Member member, Pageable pageable);


    @Query(
            value = "SELECT m " +
                    "FROM MemberMission m " +
                    "left join fetch Store " +
                    "left join fetch Mission " +
                    "WHERE Store.region.id = :region_id " +
                    "AND m.member.id IS NULL " +
                    "AND m.status = com.umc.training.domain.mission.entity.enums.MissionStatus.IN_THE_WORKS " +
                    "ORDER BY m.createdAt DESC",

            countQuery = "SELECT COUNT(m) " +
                    "FROM MemberMission m " +
                    "LEFT JOIN m.store s " +
                    "WHERE s.region.id = :region_id " +
                    "AND m.member IS NULL " +
                    "AND m.status = com.umc.training.domain.mission.entity.enums.MissionStatus.IN_THE_WORKS"
    )
    Page<MemberMission> findChallengingMissionByMember(
            @Param("region_id") Long region_id, Pageable pageable);
}
