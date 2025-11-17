package com.umc.training.domain.review;

import com.umc.training.domain.member.entity.exception.code.MemberBaseCode;
import com.umc.training.domain.member.entity.exception.code.MemberException;
import com.umc.training.domain.member.entity.repository.MemberRepository;
import com.umc.training.domain.review.dto.response.ReviewResponseDTO;
import com.umc.training.domain.review.entity.Review;
import com.umc.training.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public List<ReviewResponseDTO> getMyReview(Long userId, String query, String type) {

        log.info("memberCount : {}", memberRepository.countById(userId));
        if(memberRepository.countById(userId) == 0) {
            throw new MemberException(MemberBaseCode.MEMBER_NOT_FOUND);
        }

        List<Review> reviewList = reviewRepository.findByUserId(userId, query, type);

        return reviewList.stream()
                .map(ReviewResponseDTO::new)
                .toList();

    }

}
