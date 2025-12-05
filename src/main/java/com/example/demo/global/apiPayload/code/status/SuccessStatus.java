package com.example.demo.global.apiPayload.code.status;

import com.example.demo.global.apiPayload.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반 응답
    OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "요청 성공 및 리소스 생성됨"),

    // 회원 관련
    MEMBER_OK(HttpStatus.OK, "MEMBER200", "회원 조회 성공"),

    // 리뷰 관련
    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201", "리뷰 작성 성공"),
    REVIEW_OK(HttpStatus.OK, "REVIEW200", "리뷰 조회 성공"),

    // 미션 관련
    MISSION_OK(HttpStatus.OK, "MISSION200", "미션 조회 성공"),

    MISSION_CREATED(HttpStatus.CREATED, "MISSION201", "미션 도전 성공."),

    // 홈 관련
    HOME_OK(HttpStatus.OK, "HOME200", "홈 화면 조회 성공"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}