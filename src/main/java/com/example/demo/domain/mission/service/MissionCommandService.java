package com.example.demo.domain.mission.service;

import com.example.demo.domain.mission.dto.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO.ChallengeResultDTO challengeMission(Long memberId, Long missionId);
}