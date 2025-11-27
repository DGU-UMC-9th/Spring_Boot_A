package com.umc.training.domain.mission.exception.code;

import com.umc.training.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_NOT_FOUND_4001", "Mission not found.");

    private final HttpStatus status;
    private final String message;
    private final String code;
}

