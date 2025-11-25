package com.example.demo.domain.review.service;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {
    Page<Review> getMyReviews(Long memberId, Long storeId, Float minStar, Float maxStar, Pageable pageable);
}