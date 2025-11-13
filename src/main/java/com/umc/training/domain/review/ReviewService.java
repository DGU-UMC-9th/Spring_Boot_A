package com.umc.training.domain.review;

import com.umc.training.domain.review.dto.response.ReviewResponseDTO;
import com.umc.training.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {


    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDTO> getMyReview(String query, String type) {

        // querydsl이 필요한 순간이 왔다.
        reviewRepository.findByUserId(1L, query, type);

        return null;
    }

}
