package com.example.spring.domain.review.controller;

import com.example.spring.domain.review.entity.Review;
import com.example.spring.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/review/search/my")
    public List<Review> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Long rating
    ) {
        return reviewQueryService.searchMyReview(memberId, storeId, rating);
    }
}
