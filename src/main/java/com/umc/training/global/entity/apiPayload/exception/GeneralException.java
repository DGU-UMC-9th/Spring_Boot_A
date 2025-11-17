package com.umc.training.global.entity.apiPayload.exception;

import com.umc.training.global.entity.apiPayload.code.BaseErrorCode;
import lombok.Getter;


@Getter
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;

    public GeneralException (BaseErrorCode code) {
        this.code = code;
    }
}
