package com.example.spring.domain.review.exception;

import com.example.spring.global.apiPayload.code.BaseErrorCode;
import com.example.spring.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}