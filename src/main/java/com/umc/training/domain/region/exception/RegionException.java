package com.umc.training.domain.region.exception;

import com.umc.training.domain.region.exception.code.RegionErrorCode;
import com.umc.training.global.apiPayload.exception.GeneralException;

public class RegionException extends GeneralException {

    public RegionException(RegionErrorCode code) {
        super(code);
    }
}

