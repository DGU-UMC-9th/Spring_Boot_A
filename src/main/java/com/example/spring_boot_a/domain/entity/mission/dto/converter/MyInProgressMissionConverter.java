package com.example.spring_boot_a.domain.entity.mission.dto.converter;


import com.example.spring_boot_a.domain.entity.mission.dto.MyInProgressMissionResponse;
import com.example.spring_boot_a.domain.entity.user.UserMission;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MyInProgressMissionConverter {

    public MyInProgressMissionResponse from(UserMission um) {
        return MyInProgressMissionResponse.builder()
                .userMissionId(um.getUserMissionId())
                .missionId(um.getMission().getMissionId())
                .storeName(um.getMission().getStore().getName())
                .missionName(um.getMission().getMissionName())
                .conditional(um.getMission().getConditional())
                .point(um.getMission().getPoint())
                .deadline(um.getMission().getDeadline())
                .status(um.getStatus())
                .startedAt(um.getCreatedAt())
                .build();
    }

    public List<MyInProgressMissionResponse> fromList(List<UserMission> userMissions) {
        return userMissions.stream()
                .map(MyInProgressMissionConverter::from)
                .collect(Collectors.toList());
    }
}
