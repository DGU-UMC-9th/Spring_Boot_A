package com.example.spring.domain.member.service.command;

import com.example.spring.domain.member.dto.MemberReqDTO;
import com.example.spring.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    // 회원가입
    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );

    // 미션 도전하기
    MemberResDTO.newMissionDTO newMission(
            MemberReqDTO.newMissionDTO dto
    );
}
