package com.example.spring.domain.review.repository;

import com.example.spring.domain.review.entity.Review;

import java.util.List;
import com.querydsl.core.types.Predicate;

public interface ReviewQueryDsl {

    // 내가 작성한 리뷰 검색 API
    List<Review> searchMyReview(
            Predicate predicate
    );
}
