package com.example.spring.domain.review.service;

import com.example.spring.domain.review.entity.QReview;
import com.example.spring.domain.review.entity.Review;
import com.example.spring.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<Review> searchMyReview(
            Long memberId, Long storeId, Long rating
    ){
        // Q클래스 정의
        QReview review = QReview.review;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        builder.and(review.member.id.eq(memberId));
        if (storeId != null) builder.and(review.store.id.eq(storeId));
        if (rating  != null) builder.and(review.rating.eq(rating));

        // 리턴
        return reviewRepository.searchMyReview(builder);
    }
}
