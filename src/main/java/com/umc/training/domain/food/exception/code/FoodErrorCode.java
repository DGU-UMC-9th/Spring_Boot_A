package com.umc.training.domain.food.exception.code;

import com.umc.training.global.apiPayload.code.BaseErrorCode;
import com.umc.training.global.apiPayload.code.GeneralErrorCode;
import com.umc.training.global.apiPayload.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_4001", "Food item not found.");

    private final HttpStatus status;
    private final String message;
    private final String code;
}
