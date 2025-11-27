package com.umc.training.domain.member.entity.repository;

import com.umc.training.domain.member.entity.MemberMission;
import com.umc.training.domain.mission.entity.enums.MissionStatus;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberMissionRepository extends Repository<MemberMission, Long> {

    MemberMission save(MemberMission memberMission);

    List<MemberMission> findByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);
}
