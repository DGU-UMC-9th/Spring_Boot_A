package com.example.spring_boot_a.domain.entity.mission.dto;


import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StoreMissionSummaryResponse(
        Long missionId,
        Long storeId,
        String storeName,
        String missionName,
        String conditional,
        Integer point,
        LocalDate deadline
) {}