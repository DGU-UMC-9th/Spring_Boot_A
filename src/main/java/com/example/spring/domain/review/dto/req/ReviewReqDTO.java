package com.example.spring.domain.review.dto.req;

import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {
    public record WriteDTO(
            @NotNull
            Long memberId,
            @NotNull
            Long storeId,
            String comment,
            @NotNull
            Integer rating
    ){}
}
