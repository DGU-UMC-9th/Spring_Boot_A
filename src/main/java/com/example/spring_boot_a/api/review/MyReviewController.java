package com.example.spring_boot_a.api.review;

import com.example.spring_boot_a.api.review.dto.MyReviewItemDto;
import com.example.spring_boot_a.domain.service.MyReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MyReviewController {

    private final MyReviewQueryService service;

    @GetMapping("/users/{userId}/my-reviews")
    public Page<MyReviewItemDto> myReviews(
            @PathVariable Long userId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer starBucket,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.query(userId, storeName, starBucket, page, size);
    }
}
