package com.example.spring.domain.review.service.command;

import com.example.spring.domain.review.dto.req.ReviewReqDTO;
import com.example.spring.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {
    // 가게에 리뷰 추가
    ReviewResDTO.WriteDTO writeReview(
            ReviewReqDTO.WriteDTO dto
    );
}
