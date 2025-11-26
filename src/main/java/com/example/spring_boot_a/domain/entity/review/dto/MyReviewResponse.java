package com.example.spring_boot_a.domain.entity.review.dto;


import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MyReviewResponse(
        Long reviewId,
        String storeName,
        Double rating,
        String content,
        LocalDateTime createdAt,
        List<String> photoUrls,
        String ownerReply
) {}
