package com.umc.training.global.entity.apiPayload.code;

import org.springframework.http.HttpStatus;

public enum GeneralErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "AUTH401_1",
            "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "AUTH403_1",
            "요청이 거부되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500_1",
            "서버에 오류가 발생했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


    public HttpStatus getStatus() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


    GeneralErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
