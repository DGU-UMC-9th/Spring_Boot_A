package com.example.spring_boot_a.api.review.dto;

public record StarSummaryResponse(
        long s5,
        long s4x,
        long s3x,
        long s2x,
        long s1x,
        double avg
) {}
