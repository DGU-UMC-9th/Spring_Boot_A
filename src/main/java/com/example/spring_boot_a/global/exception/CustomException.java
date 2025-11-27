package com.example.spring_boot_a.global.exception;

import com.example.spring_boot_a.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final BaseErrorCode errorCode;

    public CustomException(BaseErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
