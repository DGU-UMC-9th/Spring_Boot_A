package com.example.spring_boot_a.domain.entity.review.dto.converter;

import com.example.spring_boot_a.domain.entity.etc.Reply;
import com.example.spring_boot_a.domain.entity.etc.ReviewPhoto;
import com.example.spring_boot_a.domain.entity.review.Review;
import com.example.spring_boot_a.domain.entity.review.dto.ReviewResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;


@UtilityClass
public class ReviewResponseConverter {

    public ReviewResponse toDto(Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getUser().getName(),                     // 작성자 이름
                review.getStar(),                               // 별점 Float 그대로
                review.getContent(),                            // 리뷰 내용
                review.getCreatedAt(),                          // Instant 타입
                extractPhotos(review),                          // 사진 리스트
                extractReplies(review)                          // 댓글 리스트
        );
    }

    private List<String> extractPhotos(Review review) {
        return review.getPhotos() == null ? List.of()
                : review.getPhotos().stream()
                .map(ReviewPhoto::getPhotoUrl) // 사진 URL 문자열 반환
                .collect(Collectors.toList());
    }

    private List<String> extractReplies(Review review) {
        return review.getReplies() == null ? List.of()
                : review.getReplies().stream()
                .map(Reply::getContent) // 댓글 내용 문자열 반환
                .collect(Collectors.toList());
    }

    public List<ReviewResponse> toDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewResponseConverter::toDto)
                .collect(Collectors.toList());
    }
}
