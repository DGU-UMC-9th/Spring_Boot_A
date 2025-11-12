package com.example.spring_boot_a.api.review.dto;

import java.time.Instant;
import java.util.List;

public record MyReviewItemDto(
        Long reviewId,
        String storeName,
        Float star,
        String content,
        Instant createdAt,
        List<String> photoUrls,
        String reply
) {}
