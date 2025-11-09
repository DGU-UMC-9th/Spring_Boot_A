package com.example.spring.domain.member.dto;

public record MemberMissionSummary(
    Long missionId,
    Boolean isSuccess,
    Long storeId,
    Long mov,
    Long point
) {}
