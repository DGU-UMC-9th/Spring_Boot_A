package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    Page<MemberMission> getChallengingMissions(Long memberId, Pageable pageable);
    Page<MemberMission> getCompletedMissions(Long memberId, Pageable pageable);
}