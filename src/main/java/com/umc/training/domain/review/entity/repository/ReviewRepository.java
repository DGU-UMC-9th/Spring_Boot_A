package com.umc.training.domain.review.entity.repository;

import com.umc.training.domain.review.entity.Review;
import org.springframework.data.repository.Repository;

public interface ReviewRepository extends Repository<Review, Long> {

    // 미션 1
    void save(Review review);

}
