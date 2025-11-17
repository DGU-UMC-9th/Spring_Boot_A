package com.example.demo.domain.review.service;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryService {

    /**
     * 내가 작성한 리뷰 조회 (필터링)
     */
    Page<Review> getMyReviews(
            Long memberId,
            Long storeId,
            Float minStar,
            Float maxStar,
            Pageable pageable
    );
}