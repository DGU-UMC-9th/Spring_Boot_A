package com.example.demo.domain.review.converter;

import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResponseDTO.CreateResultDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewListDTO toReviewListDTO(Page<Review> reviews) {
        List<ReviewResponseDTO.ReviewDetailDTO> reviewList = reviews.stream()
                .map(review -> ReviewResponseDTO.ReviewDetailDTO.builder()
                        .reviewId(review.getId())
                        .storeName(review.getStore().getName())
                        .storeCategory(review.getStore().getCategory().getDescription())
                        .star(review.getStar())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        ReviewResponseDTO.PageInfoDTO pageInfo = ReviewResponseDTO.PageInfoDTO.builder()
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();

        return ReviewResponseDTO.ReviewListDTO.builder()
                .reviews(reviewList)
                .pageInfo(pageInfo)
                .build();
    }
}