package com.umc.training.domain.mission.dto.response;

import com.umc.training.domain.member.entity.MemberMission;
import com.umc.training.domain.mission.entity.enums.MissionStatus;

import java.time.LocalDate;

public record MemberMissionResponseDTO(
        Long id,
        Long missionId,
        Long storeId,
        String storeName,
        LocalDate deadline,
        MissionStatus status,
        String missionContent,
        LocalDate createdAt
) {
    public MemberMissionResponseDTO(MemberMission memberMission) {
        this(
                memberMission.getId(),
                memberMission.getMission().getId(),
                memberMission.getStore().getId(),
                memberMission.getStore().getName(),
                memberMission.getDeadline(),
                memberMission.getStatus(),
                memberMission.getMissionContent(),
                memberMission.getCreatedAt()
        );
    }
}

