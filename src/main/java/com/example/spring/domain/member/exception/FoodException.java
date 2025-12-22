package com.example.spring.domain.member.exception;

import com.example.spring.global.apiPayload.code.BaseErrorCode;
import com.example.spring.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
