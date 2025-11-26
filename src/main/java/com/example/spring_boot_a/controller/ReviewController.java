package com.example.spring_boot_a.controller;

import com.example.spring_boot_a.domain.entity.review.dto.MyReviewResponse;
import com.example.spring_boot_a.domain.entity.review.dto.ReviewCreateRequest;
import com.example.spring_boot_a.domain.entity.review.dto.ReviewResponse;
import com.example.spring_boot_a.domain.entity.review.dto.StarSummaryResponse;
import com.example.spring_boot_a.global.apiPayload.code.ApiResponse;
import com.example.spring_boot_a.global.paging.ValidPage;
import com.example.spring_boot_a.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) { this.reviewService = reviewService; }


    @PostMapping("/stores/{storeId}/reviews")
    public Long createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,              // 실제로는 인증에서 추출
            @RequestBody @Valid ReviewCreateRequest request
    ) {
        return reviewService.create(userId, storeId, request);
    }


    @GetMapping("/stores/{storeId}/reviews")
    public Page<ReviewResponse> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(required = false) Integer starBucket, // 5 or 4 or 3 or 2 or 1
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reviewService.getStoreReviews(storeId, starBucket, pageable);
    }


    @GetMapping("/users/{userId}/reviews")
    public Page<ReviewResponse> getMyReviews(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reviewService.getMyReviews(userId, pageable);
    }


    @GetMapping("/stores/{storeId}/reviews/star-summary")
    public StarSummaryResponse getStarSummary(@PathVariable Long storeId) {
        return reviewService.getStarSummary(storeId);
    }

    @Operation(
            summary = "내가 작성한 리뷰 목록",
            description = "한 페이지에 10개씩, page는 1 이상의 정수 (0, 음수 전달 시 에러)"
    )
    @GetMapping("/me")
    public ApiResponse<ApiResponse.PageResponse<MyReviewResponse>> getMyReviews(

            @Parameter(description = "유저 ID (과제용)") @RequestParam Long userId,
            @Parameter(description = "1 이상 page 번호")
            @RequestParam @Valid @ValidPage String page
    ) {
        int pageIndex = Integer.parseInt(page) - 1; // 0-base로 변환
        return ApiResponse.onSuccess(reviewService.getMyReviews(userId, pageIndex));
    }
}
