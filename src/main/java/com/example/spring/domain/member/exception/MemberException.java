package com.example.spring.domain.member.exception;

import com.example.spring.global.apiPayload.code.BaseErrorCode;
import com.example.spring.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}