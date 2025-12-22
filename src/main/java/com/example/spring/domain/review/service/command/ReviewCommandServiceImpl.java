package com.example.spring.domain.review.service.command;

import com.example.spring.domain.review.converter.ReviewConverter;
import com.example.spring.domain.review.dto.req.ReviewReqDTO;
import com.example.spring.domain.review.dto.res.ReviewResDTO;
import com.example.spring.domain.review.entity.Review;
import com.example.spring.domain.review.exception.ReviewException;
import com.example.spring.domain.review.exception.code.ReviewErrorCode;
import com.example.spring.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    // 가게에 리뷰 추가
    @Override
    @Transactional
    public ReviewResDTO.WriteDTO writeReview(
            ReviewReqDTO.WriteDTO dto
    ) {
        Review review = ReviewConverter.toReview(dto);
        reviewRepository.save(review);

        if(dto.rating() < 1 || dto.rating() > 5) {
            throw new ReviewException(ReviewErrorCode.WRITE_REVIEW_EXCEPTION);
        }

        return ReviewConverter.toWriteDTO(review);
    }

}
