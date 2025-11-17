package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    /**
     * 내가 작성한 리뷰 조회 (동적 쿼리)
     * @param memberId 회원 ID
     * @param storeId 가게 ID (선택)
     * @param minStar 최소 별점 (선택)
     * @param maxStar 최대 별점 (선택)
     * @param pageable 페이징 정보
     * @return 리뷰 목록
     */
    Page<Review> findMyReviewsWithFilters(
            Long memberId,
            Long storeId,
            Float minStar,
            Float maxStar,
            Pageable pageable
    );
}