package com.example.spring.domain.review.converter;

import com.example.spring.domain.review.dto.req.ReviewReqDTO;
import com.example.spring.domain.review.dto.res.ReviewResDTO;
import com.example.spring.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // Review 엔티티 -> ReviewDTO
    public static ReviewResDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResDTO.ReviewDTO.builder()
                .id(review.getId())
                .storeId(review.getStore().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    // List<Review> -> List<ReviewDTO>
    public static List<ReviewResDTO.ReviewDTO> toReviewDtoList(
            List<Review> reviews
    ) {
        return reviews.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }

    // List<ReviewDTO> -> SearchMyReviewDTO
    public static ReviewResDTO.SearchMyReviewDTO toSearchMyReviewDTO(
            List<ReviewResDTO.ReviewDTO> reviews
    ) {
        return ReviewResDTO.SearchMyReviewDTO.builder()
                .reviews(reviews)
                .build();
    }

    // ReviewReqDTO.WriteDTO -> Review 엔티티
    public static Review toReview(
            ReviewReqDTO.WriteDTO dto
    ) {
        return Review.builder()
                .rating(dto.rating())
                .comment(dto.comment())
                .build();
    }

    // Review 엔티티 -> ReviewResDTO.WriteDTO
    public static ReviewResDTO.WriteDTO toWriteDTO(
            Review review
    ) {
        return ReviewResDTO.WriteDTO.builder()
                .id(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getComment())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }
}