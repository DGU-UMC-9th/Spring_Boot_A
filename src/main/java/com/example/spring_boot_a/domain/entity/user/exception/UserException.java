package com.example.spring_boot_a.domain.entity.user.exception;

import com.example.spring_boot_a.global.apiPayload.code.BaseErrorCode;
import com.example.spring_boot_a.global.exception.CustomException;

public class UserException extends CustomException {
    public UserException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
