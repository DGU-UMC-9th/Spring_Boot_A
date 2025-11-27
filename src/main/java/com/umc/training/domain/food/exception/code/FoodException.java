package com.umc.training.domain.food.exception.code;

import com.umc.training.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {

    public FoodException(FoodErrorCode code) {
        super(code);
    }

}
