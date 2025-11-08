package com.example.spring_boot_a.api.review;

import com.example.spring_boot_a.api.review.dto.*;
import com.example.spring_boot_a.domain.entity.*;
import com.example.spring_boot_a.domain.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository photoRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         ReviewPhotoRepository photoRepository,
                         ReplyRepository replyRepository,
                         UserRepository userRepository,
                         StoreRepository storeRepository) {
        this.reviewRepository = reviewRepository;
        this.photoRepository = photoRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Long create(Long userId, Long storeId, ReviewCreateRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("store not found"));

        Review r = new Review();
        r.setUser(user);
        r.setStore(store);
        r.setStar(req.star());
        r.setContent(req.content());

        Review saved = reviewRepository.save(r);

        if (req.photoUrls() != null && !req.photoUrls().isEmpty()) {
            for (String url : req.photoUrls()) {
                ReviewPhoto p = new ReviewPhoto();
                p.setReview(saved);
                p.setPhotoUrl(url);
                photoRepository.save(p);
            }
        }
        return saved.getReviewId();
    }

    public Page<ReviewResponse> getStoreReviews(Long storeId, Integer starBucket, Pageable pageable) {
        Page<Review> page;
        if (starBucket == null) {
            page = reviewRepository.findByStore_StoreIdOrderByCreatedAtDesc(storeId, pageable);
        } else if (starBucket == 5) {
            page = reviewRepository.findByStore_StoreIdAndStarEqualsOrderByCreatedAtDesc(
                    storeId, 5.0f, pageable);
        } else {
            float min = starBucket.floatValue();
            float max = starBucket + 1.0f;
            page = reviewRepository
                    .findByStore_StoreIdAndStarGreaterThanEqualAndStarLessThanOrderByCreatedAtDesc(
                            storeId, min, max, pageable);
        }
        return enrich(page);
    }

    public Page<ReviewResponse> getMyReviews(Long userId, Pageable pageable) {
        Page<Review> page = reviewRepository.findByUser_UserIdOrderByCreatedAtDesc(userId, pageable);
        return enrich(page);
    }

    public StarSummaryResponse getStarSummary(Long storeId) {
        Object[] arr = reviewRepository.countBucketsByStore(storeId);
        long s5 = ((Number) arr[0]).longValue();
        long s4 = ((Number) arr[1]).longValue();
        long s3 = ((Number) arr[2]).longValue();
        long s2 = ((Number) arr[3]).longValue();
        long s1 = ((Number) arr[4]).longValue();
        double avg = reviewRepository.findAvgStarByStore(storeId);
        return new StarSummaryResponse(s5, s4, s3, s2, s1, avg);
    }

    private Page<ReviewResponse> enrich(Page<Review> page) {
        List<Long> ids = page.stream().map(Review::getReviewId).toList();

        Map<Long, List<String>> photosByReview = photoRepository.findByReview_ReviewIdIn(ids).stream()
                .collect(Collectors.groupingBy(p -> p.getReview().getReviewId(),
                        Collectors.mapping(ReviewPhoto::getPhotoUrl, Collectors.toList())));

        Map<Long, List<String>> repliesByReview = replyRepository.findByReview_ReviewIdIn(ids).stream()
                .collect(Collectors.groupingBy(r -> r.getReview().getReviewId(),
                        Collectors.mapping(Reply::getContent, Collectors.toList())));

        List<ReviewResponse> mapped = page.stream().map(r ->
                new ReviewResponse(
                        r.getReviewId(),
                        r.getUser().getName(),
                        r.getStar(),
                        r.getContent(),
                        r.getCreatedAt(),
                        photosByReview.getOrDefault(r.getReviewId(), List.of()),
                        repliesByReview.getOrDefault(r.getReviewId(), List.of())
                )).toList();

        return new PageImpl<>(mapped, page.getPageable(), page.getTotalElements());
    }
}
