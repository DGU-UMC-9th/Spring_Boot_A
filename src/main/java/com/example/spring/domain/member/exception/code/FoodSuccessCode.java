package com.example.spring.domain.member.exception.code;

import com.example.spring.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {

    FOUND(HttpStatus.OK,
            "FOOD200_1",
            "성공적으로 업종을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}