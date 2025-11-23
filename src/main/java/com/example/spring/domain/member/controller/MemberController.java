package com.example.spring.domain.member.controller;

import com.example.spring.domain.member.dto.MemberReqDTO;
import com.example.spring.domain.member.dto.MemberResDTO;
import com.example.spring.domain.member.exception.code.MemberSuccessCode;
import com.example.spring.domain.member.service.command.MemberCommandService;
import com.example.spring.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    // 미션 도전하기
    @PostMapping("/new-mission")
    public ApiResponse<MemberResDTO.newMissionDTO> newMission(
            @RequestBody MemberReqDTO.newMissionDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberCommandService.newMission(dto));
    }
}