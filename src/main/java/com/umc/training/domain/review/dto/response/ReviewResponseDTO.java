package com.umc.training.domain.review.dto.response;

import com.umc.training.domain.review.entity.Reply;
import com.umc.training.domain.review.entity.Review;

import java.util.List;

public record ReviewResponseDTO(
        Long id,
        String contents,
        Float star,
        List<ReplyResponse> replies

) {
    public ReviewResponseDTO(Reply reply, Review review) {
        this (
                review.getId(),
                review.getContents(),
                review.getScore(),
                review.getReplyList().stream()
                        .map(ReplyResponse::new)
                        .toList()
        );
    }

    public record ReplyResponse(
            Long id,
            String contents
    ) {
        public ReplyResponse(Reply reply) {
            this (
                    reply.getId(),
                    reply.getContents()
            );
        }
    }
}
