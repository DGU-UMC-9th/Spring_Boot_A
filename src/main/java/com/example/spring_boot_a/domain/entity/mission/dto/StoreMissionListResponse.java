package com.example.spring_boot_a.domain.entity.mission.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record StoreMissionListResponse(
        int page,
        int size,
        long totalElements,
        int totalPages,
        List<StoreMissionSummaryResponse> missions
) {}
