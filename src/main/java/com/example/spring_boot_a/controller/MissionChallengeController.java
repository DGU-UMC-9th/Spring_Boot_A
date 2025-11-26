package com.example.spring_boot_a.controller;

import com.example.spring_boot_a.domain.entity.mission.dto.MissionChallengeRequest;
import com.example.spring_boot_a.domain.entity.mission.dto.MissionChallengeResponse;
import com.example.spring_boot_a.domain.entity.user.UserMission;
import com.example.spring_boot_a.global.apiPayload.code.ApiResponse;
import com.example.spring_boot_a.service.MissionChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores/{storeId}/missions")
@RequiredArgsConstructor
public class MissionChallengeController {

    private final MissionChallengeService missionChallengeService;

    @PostMapping("/{missionId}/challenge")
    public ApiResponse<MissionChallengeResponse> challengeMission(
            @PathVariable Long storeId,
            @PathVariable Long missionId,
            @RequestBody MissionChallengeRequest request
    ) {
        UserMission userMission = missionChallengeService
                .challengeMission(storeId, missionId, request.userId());

        MissionChallengeResponse response = new MissionChallengeResponse(
                userMission.getUserMissionId(),
                userMission.getMission().getMissionId(),
                userMission.getMission().getStore().getStoreId(),
                userMission.getMission().getDeadline(),
                userMission.getMission().getPoint(),
                userMission.getStatus().name()
        );

        return ApiResponse.onSuccess(response);
    }
}
