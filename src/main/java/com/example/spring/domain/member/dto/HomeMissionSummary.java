package com.example.spring.domain.member.dto;

import com.example.spring.domain.member.enums.FoodName;

import java.time.LocalDateTime;

public record HomeMissionSummary(
        Long missionId,
        Long storeId,
        Long mov,
        Long point,
        FoodName category,
        LocalDateTime expiredAt
) {}
