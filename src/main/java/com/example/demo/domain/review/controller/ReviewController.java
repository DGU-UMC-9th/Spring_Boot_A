package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.service.ReviewCommandService;
import com.example.demo.domain.review.service.ReviewQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.status.SuccessStatus;

// Swagger Annotations (springdoc)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

// Validation
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "리뷰 API", description = "리뷰 관련 API")
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
    @Operation(summary = "내 리뷰 조회", description = "내가 작성한 리뷰를 조회합니다.")
    public ApiResponse<ReviewResponseDTO.ReviewListDTO> getMyReviews(
            @Parameter(description = "회원 ID", required = true) @RequestParam Long memberId,
            @Parameter(description = "가게 ID") @RequestParam(required = false) Long storeId,
            @Parameter(description = "최소 별점") @RequestParam(required = false) Float minStar,
            @Parameter(description = "최대 별점") @RequestParam(required = false) Float maxStar,
            @Parameter(description = "페이지 번호") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "10") int size
    ) {
        Page<Review> reviews = reviewQueryService.getMyReviews(
                memberId,
                storeId,
                minStar,
                maxStar,
                PageRequest.of(page, size)
        );
        return ApiResponse.of(
                SuccessStatus.REVIEW_OK,
                ReviewConverter.toReviewListDTO(reviews)
        );
    }
}