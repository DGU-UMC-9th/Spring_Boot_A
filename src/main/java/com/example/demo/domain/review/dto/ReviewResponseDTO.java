package com.example.demo.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO {
        private Long reviewId;
        private String storeName;
        private Float star;
        private String content;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListDTO {
        private List<ReviewDetailDTO> reviews;
        private PageInfoDTO pageInfo;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDetailDTO {
        private Long reviewId;
        private String storeName;
        private String storeCategory;
        private Float star;
        private String content;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageInfoDTO {
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        String storeName;
        Float score;
        String body;
        LocalDate createdAt;
    }
}