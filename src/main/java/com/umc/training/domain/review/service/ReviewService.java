package com.umc.training.domain.review.service;

import com.umc.training.domain.member.entity.Member;
import com.umc.training.domain.member.entity.exception.code.MemberBaseCode;
import com.umc.training.domain.member.entity.exception.code.MemberException;
import com.umc.training.domain.member.entity.repository.MemberRepository;
import com.umc.training.domain.review.dto.request.StoreAddReviewRequestDTO;
import com.umc.training.domain.review.dto.response.ReviewResponseDTO;
import com.umc.training.domain.review.entity.Review;
import com.umc.training.domain.review.repository.ReviewRepository;
import com.umc.training.domain.store.entity.Store;
import com.umc.training.domain.store.exception.StoreException;
import com.umc.training.domain.store.exception.code.StoreErrorCode;
import com.umc.training.domain.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> getMyReview(Long userId, String query, String type) {

        log.info("memberCount : {}", memberRepository.countById(userId));

        if (memberRepository.countById(userId) == 0) {
            throw new MemberException(MemberBaseCode.MEMBER_NOT_FOUND);
        }

        List<Review> reviewList = reviewRepository.findByUserId(userId, query, type);

        return reviewList.stream()
                .map(ReviewResponseDTO::new)
                .toList();

    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> getMyReviewList(Long userId, int page, int size) {
        if (!memberRepository.existsById(userId)) {
            throw new MemberException(MemberBaseCode.MEMBER_NOT_FOUND);
        }

        List<Review> reviewList = reviewRepository.findAllByMemberId(userId, PageRequest.of(page, size));

        return reviewList.stream()
                .map(ReviewResponseDTO::new)
                .toList();
    }

    @Transactional
    public void addReview(Long storeId, Long userId, StoreAddReviewRequestDTO request) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberBaseCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .contents(request.contents())
                .score(request.score())
                .build();

        reviewRepository.save(review);
    }

}
