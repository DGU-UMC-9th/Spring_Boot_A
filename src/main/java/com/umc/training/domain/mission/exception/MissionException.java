package com.umc.training.domain.mission.exception;

import com.umc.training.domain.mission.exception.code.MissionErrorCode;
import com.umc.training.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(MissionErrorCode code) {
        super(code);
    }
}

