package com.umc.training.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.training.domain.review.entity.Review;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.umc.training.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final EntityManager em;

    public List<Review> findByUserId(Long userId, String query, String type) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        BooleanBuilder builder = new BooleanBuilder();

        if(type.equals("star")){
            builder.and(review.score.goe(Float.parseFloat(query)));
        }

        if(type.equals("store")){
            builder.and(review.store.name.eq(query));
        }

        builder.and(review.member.id.eq(userId));

        return queryFactory
                .selectFrom(review)
                .where(builder)
                .fetch();
    }

    public List<Review> findAllByMemberId(Long memberId, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        
        return queryFactory
                .selectFrom(review)
                .where(review.member.id.eq(memberId))
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

}
