package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.service.ReviewCommandService;
import com.example.demo.domain.review.service.ReviewQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    /**
     * 리뷰 작성
     * POST /api/reviews?memberId=1&storeId=5
     */
    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request
    ) {
        Review review = reviewCommandService.createReview(memberId, storeId, request);
        return ApiResponse.of(
                SuccessStatus.REVIEW_CREATED,
                ReviewConverter.toCreateResultDTO(review)
        );
    }

    /**
     * 내가 작성한 리뷰 조회 (QueryDSL 동적 쿼리)
     * GET /api/reviews/my?memberId=1&storeId=5&minStar=4.0&maxStar=5.0&page=0&size=10
     */
    @GetMapping("/my")
    public ApiResponse<ReviewResponseDTO.ReviewListDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Float minStar,
            @RequestParam(required = false) Float maxStar,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
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