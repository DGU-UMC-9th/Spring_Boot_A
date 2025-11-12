package com.example.spring.domain.review.exception.code;

import com.example.spring.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    // For write review
    WRITE_REVIEW_EXCEPTION(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 작성에 실패했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}