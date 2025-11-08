package com.example.spring_boot_a.domain.repository;

import com.example.spring_boot_a.api.review.dto.MyReviewItemDto;
import com.example.spring_boot_a.domain.entity.QReply;
import com.example.spring_boot_a.domain.entity.QReview;
import com.example.spring_boot_a.domain.entity.QReviewPhoto;
import com.example.spring_boot_a.domain.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory query;

    private static final QReview r = QReview.review;
    private static final QStore s = QStore.store;
    private static final QReviewPhoto p = QReviewPhoto.reviewPhoto;
    private static final QReply rp = QReply.reply;

    @Override
    public Page<MyReviewItemDto> findMyReviews(Long userId, String storeName, Integer starBucket, Pageable pageable) {


        BooleanBuilder where = new BooleanBuilder();
        where.and(r.user.userId.eq(userId));

        if (storeName != null && !storeName.isBlank()) {

            where.and(r.store.name.eq(storeName));
        }
        if (starBucket != null) {
            if (starBucket == 5) {
                where.and(r.star.eq(5.0f));
            } else {
                float min = starBucket.floatValue();
                float max = min + 1.0f;
                where.and(r.star.goe(min).and(r.star.lt(max)));
            }
        }


        List<Long> reviewIds = query
                .select(r.reviewId)
                .from(r)
                .join(r.store, s)
                .where(where)
                .orderBy(r.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (reviewIds.isEmpty()) {
            return new PageImpl<>(List.of(), pageable, 0);
        }


        Map<Long, TupleLite> base = query
                .select(r.reviewId, s.name, r.star, r.content, r.createdAt)
                .from(r)
                .join(r.store, s)
                .where(r.reviewId.in(reviewIds))
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        t -> t.get(r.reviewId),
                        t -> new TupleLite(
                                t.get(s.name),
                                t.get(r.star),
                                t.get(r.content),
                                t.get(r.createdAt))
                ));


        Map<Long, List<String>> photosByReview = query
                .select(p.review.reviewId, p.photoUrl)
                .from(p)
                .where(p.review.reviewId.in(reviewIds))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(
                        t -> t.get(p.review.reviewId),
                        Collectors.mapping(t -> t.get(p.photoUrl), Collectors.toList())
                ));


        Map<Long, String> replyByReview = query
                .select(rp.review.reviewId, rp.content, rp.replyId)
                .from(rp)
                .where(rp.review.reviewId.in(reviewIds))
                .orderBy(rp.review.reviewId.asc(), rp.replyId.desc()) // 같은 리뷰 내 최신
                .fetch()
                .stream()
                .collect(Collectors.toMap(
                        t -> t.get(rp.review.reviewId),
                        t -> t.get(rp.content),
                        (oldVal, newVal) -> oldVal // 첫 번째(최신) 유지
                ));


        List<MyReviewItemDto> content = reviewIds.stream()
                .map(id -> {
                    TupleLite b = base.get(id);
                    return new MyReviewItemDto(
                            id,
                            b.storeName,
                            b.star,
                            b.content,
                            b.createdAt,
                            photosByReview.getOrDefault(id, List.of()),
                            replyByReview.get(id)
                    );
                })
                .toList();


        Long total = query.select(r.count())
                .from(r)
                .join(r.store, s)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    // 내부 전용 최소 튜플
    private record TupleLite(String storeName, Float star, String content, java.time.Instant createdAt) {}
}
