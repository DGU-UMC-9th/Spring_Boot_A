package com.umc.training.domain.review;

import com.umc.training.domain.review.dto.response.ReviewResponseDTO;
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
    public List<ReviewResponseDTO> getMyReview(
            @RequestParam String query,
            @RequestParam String type
    ) {

        return reviewService.getMyReview(query, type);
    }

}
