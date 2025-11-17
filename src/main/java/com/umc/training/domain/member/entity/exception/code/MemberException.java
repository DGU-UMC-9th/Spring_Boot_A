package com.umc.training.domain.member.entity.exception.code;

import com.umc.training.global.entity.apiPayload.code.BaseErrorCode;
import com.umc.training.global.entity.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
