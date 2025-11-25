package com.example.demo.domain.review.service;

import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<Review> getMyReviews(Long memberId, Long storeId, Float minStar, Float maxStar, Pageable pageable) {
        return reviewRepository.findMyReviewsWithFilters(memberId, storeId, minStar, maxStar, pageable);
    }
}