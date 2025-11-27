package com.example.spring_boot_a.domain.entity.mission.dto;

import com.example.spring_boot_a.domain.entity.enums.UserMissionStatus;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;

@Builder
public record MyInProgressMissionResponse(
        Long userMissionId,
        Long missionId,
        String storeName,
        String missionName,
        String conditional,
        Integer point,
        LocalDate deadline,
        UserMissionStatus status,
        Instant startedAt
) {}
