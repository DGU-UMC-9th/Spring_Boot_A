package com.umc.training.domain.review.exception;

import com.umc.training.global.apiPayload.code.BaseErrorCode;
import com.umc.training.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {

    public ReviewException (BaseErrorCode code) {
        super(code);
    }

}
