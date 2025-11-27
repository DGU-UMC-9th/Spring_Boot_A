package com.example.spring_boot_a.service;

import com.example.spring_boot_a.domain.entity.review.dto.MyReviewItemDto;
import com.example.spring_boot_a.repository.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyReviewQueryService {
    private final ReviewQueryRepository reviewQueryRepository;

    public Page<MyReviewItemDto> query(Long userId, String storeName, Integer starBucket, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reviewQueryRepository.findMyReviews(userId, storeName, starBucket, pageable);

    }
}
