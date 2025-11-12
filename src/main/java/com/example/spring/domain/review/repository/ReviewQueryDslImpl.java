package com.example.spring.domain.review.repository;

import com.example.spring.domain.review.entity.QReview;
import com.example.spring.domain.review.entity.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    // 생성자 주입
    private final JPAQueryFactory queryFactory;

    // 내가 작성한 리뷰 검색 API
    @Override
    public List<Review> searchMyReview(
            Predicate predicate
    ){
        // Q클래스 선언
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .orderBy(review.id.desc())
                .fetch();
    }
}
