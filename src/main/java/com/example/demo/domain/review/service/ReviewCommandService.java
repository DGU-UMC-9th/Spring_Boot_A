package com.example.demo.domain.review.service;

import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.entity.Review;

public interface ReviewCommandService {
    Review createReview(Long memberId, Long storeId, ReviewRequestDTO.CreateReviewDTO request);
}