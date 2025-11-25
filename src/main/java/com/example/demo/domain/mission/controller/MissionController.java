package com.example.demo.domain.mission.controller;

import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionResponseDTO;
import com.example.demo.domain.mission.service.MissionQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionQueryService missionQueryService;

    /**
     * 진행중인 미션 조회
     * GET /api/missions/{memberId}/challenging?page=0&size=10
     */
    @GetMapping("/{memberId}/challenging")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getChallengingMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MemberMission> missions = missionQueryService.getChallengingMissions(
                memberId,
                PageRequest.of(page, size)
        );
        return ApiResponse.of(
                SuccessStatus.MISSION_OK,
                MissionConverter.toMissionListDTO(missions)
        );
    }

    /**
     * 완료한 미션 조회
     * GET /api/missions/{memberId}/completed?page=0&size=10
     */
    @GetMapping("/{memberId}/completed")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getCompletedMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MemberMission> missions = missionQueryService.getCompletedMissions(
                memberId,
                PageRequest.of(page, size)
        );
        return ApiResponse.of(
                SuccessStatus.MISSION_OK,
                MissionConverter.toMissionListDTO(missions)
        );
    }
}