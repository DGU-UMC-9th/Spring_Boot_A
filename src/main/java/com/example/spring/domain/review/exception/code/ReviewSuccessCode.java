package com.example.spring.domain.review.exception.code;

import com.example.spring.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    WRITE(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 작성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}