package com.umc.training.domain.store.exception;

import com.umc.training.domain.store.exception.code.StoreErrorCode;
import com.umc.training.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {

    public StoreException(StoreErrorCode code) {
        super(code);
    }

}
