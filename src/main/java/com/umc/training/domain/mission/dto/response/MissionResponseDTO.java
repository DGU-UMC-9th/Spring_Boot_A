package com.umc.training.domain.mission.dto.response;

import com.umc.training.domain.mission.entity.Mission;

import java.time.LocalDate;

public record MissionResponseDTO(
        Long id,
        Integer reward,
        LocalDate deadline,
        String missionSpec
) {
    public MissionResponseDTO(Mission mission) {
        this(
                mission.getId(),
                mission.getReward(),
                mission.getDeadline(),
                mission.getMissionSpec()
        );
    }
}

