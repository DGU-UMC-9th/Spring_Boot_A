package com.example.spring_boot_a.domain.entity.review.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public record ReviewCreateRequest(
        @NotNull Float star,
        @NotBlank String content,
        List<@Size(max = 500) String> photoUrls
) {}