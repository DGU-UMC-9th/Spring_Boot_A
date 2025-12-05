package com.example.demo.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateReviewDTO {

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
        private Float star;

        // 이미지는 선택사항
        private List<String> imageUrls;
    }
}