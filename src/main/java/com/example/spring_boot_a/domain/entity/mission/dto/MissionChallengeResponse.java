package com.example.spring_boot_a.domain.entity.mission.dto;

import java.time.LocalDate;

public record MissionChallengeResponse(Long userMissionId,
                                       Long missionId,
                                       Long storeId,
                                       LocalDate deadline,
                                       Integer point,
                                       String status) {
}
