package com.umc.training.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.training.domain.review.entity.QReview;
import com.umc.training.domain.review.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.umc.training.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final EntityManager em;

    public List<Review> findByUserId(Long userId, String query, String type) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        BooleanBuil builder = new BooleanBuilder();

        if(type.equals("star")){
            builder.and(review.score.goe(Float.parseFloat(query)));
        }

        if(type.equals("store")){
            builder.and(review.store.name.eq(query));
        }
        // 가게가 있다면 가게 조건 추가
        // 가게가 없다면 전체 조회
        // 평점이 있다면 평점 조건 추가
        // 평점이 없다면 전체 조회
        // input은 조건이 어떻게 들어올까
        return queryFactory
                .selectFrom(review)
                .where(builder)
                .fetch();
    }



}
