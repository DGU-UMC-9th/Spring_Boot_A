package com.umc.training.domain.mission.controller;

import com.umc.training.domain.mission.service.MissionService;
import com.umc.training.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @PostMapping("/{missionId}/user/{userId}/challenge")
    public ApiResponse<Void> challengeMission(
            @PathVariable("missionId") Long missionId,
            @PathVariable("userId") Long userId) {

        missionService.challengeMission(missionId, userId);
        return ApiResponse.onSuccess(null);
    }
}

