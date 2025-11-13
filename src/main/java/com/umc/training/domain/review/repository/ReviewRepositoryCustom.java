package com.umc.training.domain.review.repository;

import com.umc.training.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findByUserId(Long userId, String query, String type);
}
