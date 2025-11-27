package com.example.spring_boot_a.controller;


import com.example.spring_boot_a.domain.entity.mission.dto.MissionChallengeRequest;
import com.example.spring_boot_a.domain.entity.mission.dto.MissionChallengeResponse;
import com.example.spring_boot_a.domain.entity.mission.dto.MyInProgressMissionResponse;
import com.example.spring_boot_a.domain.entity.mission.dto.StoreMissionListResponse;
import com.example.spring_boot_a.domain.entity.user.UserMission;
import com.example.spring_boot_a.global.apiPayload.code.ApiResponse;
import com.example.spring_boot_a.global.paging.ValidPage;
import com.example.spring_boot_a.service.MissionChallengeService;
import com.example.spring_boot_a.service.MyInProgressMissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션", description = "미션 조회/도전 API")
@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionChallengeService missionChallengeService;
    private final MyInProgressMissionService myInProgressMissionService;


    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "한 페이지에 10개씩 조회. page 는 1 이상의 정수 (0, 음수 전달 시 에러)."
    )
    @GetMapping("/stores/{storeId}")
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

    @Operation(
            summary = "미션 도전하기",
            description = "특정 가게의 특정 미션을 사용자(userId)가 도전 시작."
    )
    @PostMapping("/stores/{storeId}/{missionId}/challenge")
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
            summary = "내가 진행중인 미션 목록",
            description = "UserMission status = IN_PROGRESS 인 미션을 10개씩 페이징 조회"
    )
    @GetMapping("/me/in-progress")
    public ApiResponse<ApiResponse.PageResponse<MyInProgressMissionResponse>> getMyInProgress(
            // TODO: 실제 구현에서는 로그인 유저에서 userId 가져오면 됨
            @Parameter(description = "유저 ID (과제용)") @RequestParam Long userId,
            @Parameter(description = "1 이상 page 번호")
            @RequestParam @Valid @ValidPage String page
    ) {
        int pageIndex = Integer.parseInt(page) - 1;

        ApiResponse.PageResponse<MyInProgressMissionResponse> result =
                myInProgressMissionService.getMyInProgressMissions(userId, pageIndex);

        return ApiResponse.onSuccess(result);
    }
}