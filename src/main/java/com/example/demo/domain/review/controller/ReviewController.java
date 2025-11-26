package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.service.ReviewCommandService;
import com.example.demo.domain.review.service.ReviewQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.status.SuccessStatus;
import com.example.demo.global.validation.annotation.CheckPage;
import org.springframework.validation.annotation.Validated;

// Swagger Annotations (springdoc)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

// Validation
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "리뷰 API", description = "리뷰 관련 API")
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 작성합니다. 이미지는 선택사항입니다.")
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId,
            @Parameter(description = "가게 ID", required = true) @RequestParam Long storeId,
            @Valid @RequestBody ReviewRequestDTO.CreateReviewDTO request
    ) {
        Review review = reviewCommandService.createReview(memberId, storeId, request);
        return ApiResponse.of(
                SuccessStatus.REVIEW_CREATED,
                ReviewConverter.toCreateResultDTO(review)
        );
    }

    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "내가 작성한 리뷰 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getMyReviews(
            @Parameter(description = "회원 ID", required = true)
            @RequestParam Long memberId,

            @Parameter(description = "페이지 번호 (1부터 시작)", required = false)
            @RequestParam(defaultValue = "1")
            @CheckPage
            Integer page
    ) {
        ReviewResponseDTO.ReviewPreViewListDTO result = reviewQueryService.getMyReviews(memberId, page);
        return ApiResponse.of(SuccessStatus.REVIEW_OK, result);
    }
}