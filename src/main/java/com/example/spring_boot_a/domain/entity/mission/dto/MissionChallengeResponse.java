package com.example.spring_boot_a.domain.entity.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MissionChallengeResponse(Long userMissionId,
                                       Long missionId,
                                       Long storeId,
                                       LocalDate deadline,
                                       Integer point,
                                       String status) {
}
