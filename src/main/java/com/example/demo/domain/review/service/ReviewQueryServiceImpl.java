package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.global.apiPayload.code.status.ErrorStatus;
import com.example.demo.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<Review> getMyReviews(Long memberId, Long storeId, Float minStar, Float maxStar, Pageable pageable) {
        // 회원 존재 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 기존 Repository 메서드 사용 (간단한 조회)
        return reviewRepository.findByMemberOrderByCreatedAtDesc(member, pageable);
    }
}