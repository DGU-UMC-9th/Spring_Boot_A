package com.example.spring.domain.store.exception.code;

import com.example.spring.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    FOUND(HttpStatus.OK,
            "STORE200_2",
            "성공적으로 가게를 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
