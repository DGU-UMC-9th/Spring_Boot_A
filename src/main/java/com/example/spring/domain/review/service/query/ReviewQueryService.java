package com.example.spring.domain.review.service.query;

import com.example.spring.domain.review.dto.res.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    );
}
