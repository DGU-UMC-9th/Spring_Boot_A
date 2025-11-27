package com.example.demo.domain.mission.controller;

import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionResponseDTO;
import com.example.demo.domain.mission.service.MissionCommandService;
import com.example.demo.domain.mission.service.MissionQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.example.demo.global.validation.annotation.CheckPage;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Tag(name = "미션 API", description = "미션 관련 API")
public class MissionController {

    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;

    @GetMapping("/{memberId}/challenging")
    @Operation(summary = "진행중인 미션 조회", description = "회원의 진행중인 미션을 조회합니다.")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getChallengingMissions(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long memberId,
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size
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

    @GetMapping("/{memberId}/completed")
    @Operation(summary = "완료한 미션 조회", description = "회원이 완료한 미션을 조회합니다.")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getCompletedMissions(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long memberId,
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size
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

    // 미션 도전하기
    @PostMapping("/{missionId}/challenge")
    @Operation(summary = "미션 도전하기", description = "특정 미션에 도전합니다.")
    public ApiResponse<MissionResponseDTO.ChallengeResultDTO> challengeMission(
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId,
            @Parameter(description = "미션 ID", required = true) @PathVariable Long missionId
    ) {
        MissionResponseDTO.ChallengeResultDTO result = missionCommandService.challengeMission(memberId, missionId);
        return ApiResponse.of(
                SuccessStatus.MISSION_CREATED,
                result
        );
    }

    @GetMapping("/stores/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 미션 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "가게를 찾을 수 없음")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getStoreMissions(
            @Parameter(description = "가게 ID", required = true)
            @PathVariable Long storeId,

            @Parameter(description = "페이지 번호 (1부터 시작)", required = false)
            @RequestParam(defaultValue = "1")
            @CheckPage
            Integer page
    ) {
        MissionResponseDTO.MissionPreViewListDTO result = missionQueryService.getStoreMissions(storeId, page);
        return ApiResponse.of(SuccessStatus.MISSION_OK, result);
    }
}