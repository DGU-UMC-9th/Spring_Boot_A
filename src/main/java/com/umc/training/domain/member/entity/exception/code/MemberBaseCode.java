package com.umc.training.domain.member.entity.exception.code;

import com.umc.training.global.entity.apiPayload.code.BaseErrorCode;
import com.umc.training.global.entity.apiPayload.exception.GeneralException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberBaseCode implements BaseErrorCode {

    MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND, "MEMBER-001");

    private final String message;
    private final HttpStatus status;
    private final String code;
}
