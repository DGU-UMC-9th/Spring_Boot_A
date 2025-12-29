package com.example.spring.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    @Getter
    public static class ReviewDTO {
        private long id;
        private long storeId;
        private long rating;
        private String comment;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    public static class SearchMyReviewDTO {
        private List<ReviewDTO> reviews;
    }

    @Builder
    public static class WriteDTO {
        private long id;
        private LocalDateTime createdAt;
    }

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Long score,
            String body,
            LocalDate createdAt
    ){}
}
