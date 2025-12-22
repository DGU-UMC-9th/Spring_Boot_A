package com.example.spring.domain.review.controller;

import com.example.spring.domain.review.converter.ReviewConverter;
import com.example.spring.domain.review.dto.req.ReviewReqDTO;
import com.example.spring.domain.review.dto.res.ReviewResDTO;
import com.example.spring.domain.review.exception.code.ReviewSuccessCode;
import com.example.spring.domain.review.service.command.ReviewCommandService;
import com.example.spring.domain.review.service.query.ReviewQueryService;
import com.example.spring.global.apiPayload.ApiResponse;
import com.example.spring.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    // 내가 작성한 리뷰 검색 API
    @GetMapping("/search")
    public ApiResponse<ReviewResDTO.SearchMyReviewDTO> searchMyReview(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating
    ) {
        // 1) 서비스에서 DTO 리스트 조회
        List<ReviewResDTO.ReviewDTO> reviews =
                reviewQueryService.searchMyReview(memberId, storeId, rating);

        // 2) reviews 리스트를 SearchMyReviewDTO 로 감싸기
        ReviewResDTO.SearchMyReviewDTO body =
                ReviewConverter.toSearchMyReviewDTO(reviews);

        // 3) 글로벌 응답 포맷으로 다시 감싸서 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, body);
    }

    @PostMapping
    public ApiResponse<ReviewResDTO.WriteDTO> writeReview(
            @RequestBody ReviewReqDTO.WriteDTO dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.WRITE,
                reviewCommandService.writeReview(dto));
    }
}
