package com.umc.training.domain.mission.controller;

import com.umc.training.domain.mission.dto.response.MemberMissionResponseDTO;
import com.umc.training.domain.mission.dto.response.MissionResponseDTO;
import com.umc.training.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "미션", description = "미션 관련 API")
public interface MissionControllerDocs {

    @Operation(
            summary = "미션 도전하기",
            description = "특정 미션을 사용자의 진행 중인 미션 목록에 추가합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "미션 도전 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<Void> challengeMission(
            @Parameter(description = "미션 ID", required = true) Long missionId,
            @Parameter(description = "사용자 ID", required = true) Long userId
    );

    @Operation(
            summary = "특정 가게의 미션 목록 조회",
            description = "페이징을 적용하여 특정 가게의 모든 미션을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<List<MissionResponseDTO>> getStoreMissions(
            @Parameter(description = "가게 ID", required = true) Long storeId,
            @Parameter(description = "페이지 번호 (0부터 시작)", required = true) int page,
            @Parameter(description = "페이지 크기", required = true) int size
    );

    @Operation(
            summary = "내가 진행중인 미션 목록 조회",
            description = "페이징을 적용하여 사용자가 진행 중인 모든 미션을 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<List<MemberMissionResponseDTO>> getMyInProgressMissions(
            @Parameter(description = "사용자 ID", required = true) Long userId,
            @Parameter(description = "페이지 번호 (0부터 시작)", required = true) int page,
            @Parameter(description = "페이지 크기", required = true) int size
    );
}

