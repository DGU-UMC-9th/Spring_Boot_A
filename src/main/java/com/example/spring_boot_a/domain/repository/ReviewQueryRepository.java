package com.example.spring_boot_a.domain.repository;

import com.example.spring_boot_a.api.review.dto.MyReviewItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {
    Page<MyReviewItemDto> findMyReviews(
            Long userId,
            String storeName,
            Integer starBucket,
            Pageable pageable
    );
}
