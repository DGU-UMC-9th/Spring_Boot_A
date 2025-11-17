package com.example.demo.domain.mission.converter;

import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.mission.dto.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionListDTO toMissionListDTO(Page<MemberMission> memberMissions) {
        List<MissionResponseDTO.MissionDetailDTO> missionList = memberMissions.stream()
                .map(mm -> {
                    // D-Day 계산
                    long daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), mm.getMission().getDeadline());
                    String dDay = daysUntil >= 0 ? "D-" + daysUntil : "마감";

                    return MissionResponseDTO.MissionDetailDTO.builder()
                            .storeName(mm.getMission().getStore().getName())
                            .missionContent(mm.getMission().getContent())
                            .point(mm.getMission().getPoint())
                            .deadline(mm.getMission().getDeadline())
                            .startedAt(mm.getCreatedAt())
                            .completedAt(mm.getCompletedAt())
                            .dDay(dDay)
                            .build();
                })
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDTO.builder()
                .missions(missionList)
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }
}