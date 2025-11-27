package com.umc.training.domain.region.exception.code;

import com.umc.training.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RegionErrorCode implements BaseErrorCode {

    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION_NOT_FOUND_4001", "Region not found.");

    private final HttpStatus status;
    private final String message;
    private final String code;
}

