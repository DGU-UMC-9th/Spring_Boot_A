package com.example.spring.domain.review.controller;

import com.example.spring.domain.review.dto.res.ReviewResDTO;
import com.example.spring.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {

    @Operation(
            summary = "가게의 리뷰 목록 조회 API By 슝 (개발 중)", // api 제목 옆에 붙는 거
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다." // api 클릭하면 안에 뜨는 설명
    )
    @ApiResponses({ // 응답 코드에 따른 설명
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviews(
            @RequestParam String storeName,
            @RequestParam(defaultValue = "1") Integer page);
}
