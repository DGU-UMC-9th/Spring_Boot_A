package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.converter.MemberConverter;
import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.status.SuccessStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberQueryService memberQueryService;

    /**
     * 마이페이지 조회
     * GET /api/members/{memberId}/mypage
     */
    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MemberResponseDTO.MyPageDTO> getMyPage(@PathVariable Long memberId) {
        Member member = memberQueryService.getMyPageInfo(memberId);
        return ApiResponse.of(
                SuccessStatus.MEMBER_OK,
                MemberConverter.toMyPageDTO(member)
        );
    }
}