package com.example.spring_boot_a.domain.entity.review.dto.converter;

import com.example.spring_boot_a.domain.entity.review.Review;
import com.example.spring_boot_a.domain.entity.review.dto.MyReviewResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MyReviewConverter {

    public MyReviewResponse from(Review review) {
        return MyReviewResponse.builder()
                .reviewId(review.getReviewId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .photoUrls(
                        review.getReviewPhotos().stream()
                                .map(photo -> photo.getImageUrl())
                                .collect(Collectors.toList())
                )
                .ownerReply(
                        review.getReply() != null ? review.getReply().getContent() : null
                )
                .ownerReplyCreatedAt(
                        review.getReply() != null ? review.getReply().getCreatedAt() : null
                )
                .build();
    }

    public List<MyReviewResponse> fromList(List<Review> reviews) {
        return reviews.stream()
                .map(MyReviewConverter::from)
                .collect(Collectors.toList());
    }
}