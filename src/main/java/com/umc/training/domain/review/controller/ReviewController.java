package com.umc.training.domain.review.controller;

import com.umc.training.domain.review.service.ReviewService;
import com.umc.training.domain.review.dto.request.StoreAddReviewRequestDTO;
import com.umc.training.domain.review.dto.response.ReviewResponseDTO;
import com.umc.training.global.apiPayload.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<ReviewResponseDTO>> getMyReview(
            @RequestParam("userId") Long userId,
            @RequestParam("query") String query,
            @RequestParam("type") String type) {

        return ApiResponse.onSuccess(reviewService.getMyReview(userId, query, type));
    }

    // 가게에 리뷰 추가하기
    @PostMapping("/store/{storeId}/user/{userId}")
    public ApiResponse<Void> addReview(
            @PathVariable("storeId") Long storeId,
            @PathVariable("userId") Long userId,
            @RequestBody StoreAddReviewRequestDTO request) {

        reviewService.addReview(storeId, userId, request);
        return ApiResponse.onSuccess(null);
    }

}
