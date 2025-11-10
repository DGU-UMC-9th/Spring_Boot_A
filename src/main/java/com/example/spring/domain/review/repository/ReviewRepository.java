package com.example.spring.domain.review.repository;

import com.example.spring.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    // 리뷰 작성하는 쿼리
    // INSERT INTO review(member_id, store_id, rating, comment)
    // VALUES (1, 1, 5, '음 너무 맛있어요... 어쩌구');
    Review save(Review review);
}
