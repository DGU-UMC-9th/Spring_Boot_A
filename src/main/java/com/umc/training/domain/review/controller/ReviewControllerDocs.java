package com.umc.training.domain.review.controller;

import com.umc.training.domain.review.dto.request.StoreAddReviewRequestDTO;
import com.umc.training.domain.review.dto.response.ReviewResponseDTO;
import com.umc.training.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "리뷰", description = "리뷰 관련 API")
public interface ReviewControllerDocs {

    @Operation(
            summary = "내 리뷰 조회 (필터링)",
            description = "사용자 ID, 쿼리, 타입을 기반으로 리뷰를 필터링하여 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<List<ReviewResponseDTO>> getMyReview(
            @Parameter(description = "사용자 ID", required = true) Long userId,
            @Parameter(description = "검색 쿼리", required = true) String query,
            @Parameter(description = "필터 타입 (star 또는 store)", required = true) String type
    );

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회",
            description = "페이징을 적용하여 사용자가 작성한 모든 리뷰를 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<List<ReviewResponseDTO>> getMyReviewList(
            @Parameter(description = "사용자 ID", required = true) Long userId,
            @Parameter(description = "페이지 번호 (0부터 시작)", required = true) int page,
            @Parameter(description = "페이지 크기", required = true) int size
    );

    @Operation(
            summary = "가게에 리뷰 추가",
            description = "특정 가게에 리뷰를 작성합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "리뷰 작성 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<Void> addReview(
            @Parameter(description = "가게 ID", required = true) Long storeId,
            @Parameter(description = "사용자 ID", required = true) Long userId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "리뷰 작성 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = StoreAddReviewRequestDTO.class))
            ) StoreAddReviewRequestDTO request
    );
}

