package com.umc.training.domain.store.exception.code;

import com.umc.training.global.apiPayload.code.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE_NOT_FOUND_4001", "Store not found.");

    private final HttpStatus status;
    private final String message;
    private final String code;

}
