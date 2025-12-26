package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.mission.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionQueryService {
    // 진행중인 미션 조회
    Page<MemberMission> getChallengingMissions(Long memberId, Pageable pageable);

    // 특정 가게의 미션 목록 조회
    MissionResponseDTO.MissionPreViewListDTO getStoreMissions(Long storeId, Integer page);

    // 내가 진행중인 미션 목록 조회
    MissionResponseDTO.MemberMissionPreViewListDTO getMyChallengingMissions(Long memberId, Integer page);
}