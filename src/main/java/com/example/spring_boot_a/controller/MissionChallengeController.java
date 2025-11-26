package com.example.spring_boot_a.controller;

import com.example.spring_boot_a.domain.entity.mission.dto.MissionChallengeRequest;
import com.example.spring_boot_a.domain.entity.mission.dto.MissionChallengeResponse;
import com.example.spring_boot_a.domain.entity.mission.dto.StoreMissionListResponse;
import com.example.spring_boot_a.domain.entity.user.UserMission;
import com.example.spring_boot_a.global.apiPayload.code.ApiResponse;
import com.example.spring_boot_a.global.paging.ValidPage;
import com.example.spring_boot_a.service.MissionChallengeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
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

    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "한 페이지에 10개씩 조회. page 는 1 이상의 정수 (0, 음수 전달 시 에러)."
    )
    @GetMapping
    public ApiResponse<StoreMissionListResponse> getStoreMissions(
            @PathVariable Long storeId,
            @Parameter(description = "1 이상의 page 번호")
            @RequestParam @Valid @ValidPage String page
    ) {
        int pageIndex = Integer.parseInt(page) - 1;   // 0-base 로 변환
        StoreMissionListResponse response =
                missionChallengeService.getStoreMissions(storeId, pageIndex);
        return ApiResponse.onSuccess(response);
    }
}
