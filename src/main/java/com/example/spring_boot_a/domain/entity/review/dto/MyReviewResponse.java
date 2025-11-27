package com.example.spring_boot_a.domain.entity.review.dto;


import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record MyReviewResponse(
        Long reviewId,
        String storeName,
        Double rating,
        String content,
        Instant createdAt,
        List<String> photoUrls,
        String ownerReply,
        Instant ownerReplyCreatedAt
) {}
