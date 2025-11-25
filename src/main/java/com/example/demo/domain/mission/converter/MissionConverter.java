package com.example.demo.domain.mission.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.mission.dto.MissionResponseDTO;
import com.example.demo.domain.mission.entity.Mission;
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

    // 미션 도전용 DTO -> Entity
    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();
    }

    // Entity -> 미션 도전 결과 DTO
    public static MissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        return MissionResponseDTO.ChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .startedAt(memberMission.getCreatedAt())
                .build();
    }
}