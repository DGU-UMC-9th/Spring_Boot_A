package com.umc.training.domain.review;

import com.umc.training.domain.review.dto.response.ReviewResponseDTO;
import com.umc.training.global.entity.apiPayload.ApiResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<ReviewResponseDTO>> getMyReview(
            @RequestParam("userId") Long userId,
            @RequestParam("query") String query,
            @RequestParam("type") String type
    ) {

        return ApiResponse.onSuccess(reviewService.getMyReview(userId, query, type));
    }

}
