package com.umc.training.domain.mission.controller;

import com.umc.training.domain.mission.dto.response.MemberMissionResponseDTO;
import com.umc.training.domain.mission.dto.response.MissionResponseDTO;
import com.umc.training.domain.mission.service.MissionService;
import com.umc.training.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController implements MissionControllerDocs {

    private final MissionService missionService;

    @Override
    @PostMapping("/{missionId}/user/{userId}/challenge")
    public ApiResponse<Void> challengeMission(
            @PathVariable("missionId") Long missionId,
            @PathVariable("userId") Long userId) {

        missionService.challengeMission(missionId, userId);
        return ApiResponse.onSuccess(null);
    }

    @Override
    @GetMapping("/store/{storeId}")
    public ApiResponse<List<MissionResponseDTO>> getStoreMissions(
            @PathVariable("storeId") Long storeId,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        return ApiResponse.onSuccess(missionService.getStoreMissions(storeId, page, size));
    }

    @Override
    @GetMapping("/user/{userId}/in-progress")
    public ApiResponse<List<MemberMissionResponseDTO>> getMyInProgressMissions(
            @PathVariable("userId") Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        return ApiResponse.onSuccess(missionService.getMyInProgressMissions(userId, page, size));
    }
}
