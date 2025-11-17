package com.umc.training.domain.review.exception.code;

import com.umc.training.global.entity.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW_NOT_FOUND_4001", "리뷰를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
    private final String code;


}
