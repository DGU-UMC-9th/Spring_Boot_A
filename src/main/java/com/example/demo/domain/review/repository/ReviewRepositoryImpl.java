package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.demo.domain.review.entity.QReview.review;
import static com.example.demo.domain.member.entity.QMember.member;
import static com.example.demo.domain.store.entity.QStore.store;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviews(Long memberId, Long storeId, Float minStar, Float maxStar, Pageable pageable) {
        // 동적 쿼리로 조건에 맞는 리뷰 조회
        List<Review> reviews = queryFactory
                .selectFrom(review)
                .join(review.member, member).fetchJoin()
                .join(review.store, store).fetchJoin()
                .where(
                        memberIdEq(memberId),
                        storeIdEq(storeId),
                        starGoe(minStar),
                        starLoe(maxStar)
                )
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 개수 조회
        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        memberIdEq(memberId),
                        storeIdEq(storeId),
                        starGoe(minStar),
                        starLoe(maxStar)
                )
                .fetchOne();

        return new PageImpl<>(reviews, pageable, total != null ? total : 0L);
    }

    // 동적 쿼리 조건 메서드들
    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? review.member.id.eq(memberId) : null;
    }

    private BooleanExpression storeIdEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }

    private BooleanExpression starGoe(Float minStar) {
        return minStar != null ? review.star.goe(minStar) : null;
    }

    private BooleanExpression starLoe(Float maxStar) {
        return maxStar != null ? review.star.loe(maxStar) : null;
    }
}