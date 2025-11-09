package com.example.spring.domain.member.repository;

import com.example.spring.domain.member.dto.HomeMissionSummary;
import com.example.spring.domain.member.dto.MemberMissionSummary;
import com.example.spring.domain.member.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    // 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
    //SELECT mm.mission_id, is_success, store_id, mov, point
    //FROM member_mission as mm LEFT OUTER JOIN mission as m
    //ON mm.mission_id = m.mission_id
    //WHERE member_id = 1 AND mm.id < 3 // 가정
    //// 1은 해당하는 회원, 3은 마지막으로 조회한 데이터 id 값
    //ORDER BY mm.id DESC
    //LIMIT 10;
    @Query("""
        select new com.example.spring.domain.member.dto.MemberMissionSummary(
            m.id,
            mm.isSuccess,
            m.store.id,
            m.mov,
            m.point
        )
        from MemberMission mm
        left join mm.mission m
        where mm.member.id = :memberId
          and (:lastId is null or mm.id < :lastId)
        order by mm.id desc
        """)
    Slice<MemberMissionSummary> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("lastId") Long lastId,
            Pageable pageable
    );

    //홈 화면 쿼리(현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)
    //SELECT mm.mission_id, store_id, mov, point, category, expired_at
    //FROM member_mission as mm LEFT OUTER JOIN mission as m
    //ON mm.mission_id = m.mission_id
    //LEFT OUTER JOIN store as s
    //ON m.store_id = s.id
    //WHERE member_id = 1 AND neighborhood_id = '안암동' AND m.id < 3 AND is_success = false // 가정
    //ORDER BY m.id DESC
    //LIMIT 10;
    @Query("""
        select new com.example.spring.domain.member.dto.HomeMissionSummary(
            m.id,
            s.id,
            m.mov,
            m.point,
            s.category,
            m.expiredAt
        )
        from MemberMission mm
        left join mm.mission m
        left join m.store s
        where mm.member.id = :memberId
          and s.neighborhood = :neighborhoodId
          and mm.isSuccess = false
          and (:lastMissionId is null or m.id < :lastMissionId)
        order by m.id desc
        """)
    Slice<HomeMissionSummary> findHomeMissions(
            @Param("memberId") Long memberId,
            @Param("neighborhoodId") String neighborhoodId,
            @Param("lastMissionId") Long lastMissionId,
            Pageable pageable
    );
}
