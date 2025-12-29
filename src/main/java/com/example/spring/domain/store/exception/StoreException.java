package com.example.spring.domain.store.exception;

import com.example.spring.global.apiPayload.code.BaseErrorCode;
import com.example.spring.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
