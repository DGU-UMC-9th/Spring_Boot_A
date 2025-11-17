package com.example.demo.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {

    @Getter
    @NoArgsConstructor
    public static class CreateReviewDTO {
        private String content;
        private Float star;
    }
}