package com.example.spring.domain.test.exception;
import com.example.spring.global.apiPayload.code.BaseErrorCode;
import com.example.spring.global.apiPayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}
