package com.example.spring.domain.review.service.query;

import com.example.spring.domain.review.converter.ReviewConverter;
import com.example.spring.domain.review.dto.res.ReviewResDTO;
import com.example.spring.domain.review.entity.QReview;
import com.example.spring.domain.review.entity.Review;
import com.example.spring.domain.review.exception.ReviewException;
import com.example.spring.domain.review.exception.code.ReviewErrorCode;
import com.example.spring.domain.review.repository.ReviewRepository;
import com.example.spring.domain.store.entity.Store;
import com.example.spring.domain.store.exception.StoreException;
import com.example.spring.domain.store.exception.code.StoreErrorCode;
import com.example.spring.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public List<ReviewResDTO.ReviewDTO> searchMyReview(
            Long memberId, Long storeId, Long rating
    ){
        if (memberId == null || memberId <= 0) {
            throw new ReviewException(ReviewErrorCode.SEARCH_REVIEW_EXCEPTION);
        }

        if (rating != null && (rating < 1 || rating > 5)) {
            throw new ReviewException(ReviewErrorCode.SEARCH_REVIEW_EXCEPTION);
        }

        if (storeId != null && storeId <= 0) {
            throw new ReviewException(ReviewErrorCode.SEARCH_REVIEW_EXCEPTION);
        }

        // Q클래스 정의
        QReview review = QReview.review;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        // 동적 쿼리: 검색 조건
        builder.and(review.member.id.eq(memberId));
        if (storeId != null) builder.and(review.store.id.eq(storeId));
        if (rating  != null) builder.and(review.rating.eq(rating));

        // 1) Querydsl로 엔티티 리스트 조회
        List<Review> reviews = reviewRepository.searchMyReview(builder);

        // 2) 엔티티 -> DTO 리스트 변환
        return ReviewConverter.toReviewDtoList(reviews);
    }

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
