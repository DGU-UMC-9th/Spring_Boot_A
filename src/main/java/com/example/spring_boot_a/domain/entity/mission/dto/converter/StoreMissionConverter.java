package com.example.spring_boot_a.domain.entity.mission.dto.converter;

import com.example.spring_boot_a.domain.entity.mission.Mission;
import com.example.spring_boot_a.domain.entity.mission.dto.StoreMissionSummaryResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class StoreMissionConverter {

    public StoreMissionSummaryResponse toSummary(Mission mission) {
        return StoreMissionSummaryResponse.builder()
                .missionId(mission.getMissionId())
                .storeId(mission.getStore().getStoreId())
                .storeName(mission.getStore().getName())
                .missionName(mission.getMissionName())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public List<StoreMissionSummaryResponse> toSummaryList(List<Mission> missions) {
        return missions.stream()
                .map(StoreMissionConverter::toSummary)
                .collect(Collectors.toList());
    }
}
