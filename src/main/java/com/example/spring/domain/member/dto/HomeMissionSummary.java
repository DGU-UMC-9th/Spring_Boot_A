package com.example.spring.domain.member.dto;

import com.example.spring.domain.member.enums.CategoryName;

import java.time.LocalDateTime;

public record HomeMissionSummary(
        Long missionId,
        Long storeId,
        Long mov,
        Long point,
        CategoryName category,
        LocalDateTime expiredAt
) {}
