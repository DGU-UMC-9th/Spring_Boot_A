package com.example.spring_boot_a.domain.entity.review.dto;

import java.time.Instant;
import java.util.List;

public record ReviewResponse(
        Long reviewId,
        String userName,
        Float star,
        String content,
        Instant createdAt,
        List<String> photos,
        List<String> replies
) {}
