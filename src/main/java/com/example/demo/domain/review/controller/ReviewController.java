package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.service.ReviewCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    // 리뷰 작성 POST /api/reviews?memberId=1&storeId=5
    @PostMapping
    public ReviewResponseDTO.CreateResultDTO createReview(
            @RequestParam Long memberId,
            @RequestParam Long storeId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request
    ) {
        Review review = reviewCommandService.createReview(memberId, storeId, request);
        return ReviewConverter.toCreateResultDTO(review);
    }
}