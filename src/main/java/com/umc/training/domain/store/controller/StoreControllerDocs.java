package com.umc.training.domain.store.controller;

import com.umc.training.domain.store.dto.request.StoreAddRequestDTO;
import com.umc.training.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "가게", description = "가게 관련 API")
public interface StoreControllerDocs {

    @Operation(
            summary = "특정 지역에 가게 추가",
            description = "지역 ID를 받아 해당 지역에 새로운 가게를 등록합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "가게 등록 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<Void> addStore(
            @Parameter(description = "지역 ID", required = true) Long regionId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "가게 등록 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = StoreAddRequestDTO.class))
            ) StoreAddRequestDTO request
    );
}

