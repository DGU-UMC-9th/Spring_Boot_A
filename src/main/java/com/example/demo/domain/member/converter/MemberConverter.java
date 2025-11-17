package com.example.demo.domain.member.converter;

import com.example.demo.domain.member.dto.MemberResponseDTO;
import com.example.demo.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResponseDTO.MyPageDTO toMyPageDTO(Member member) {
        // 전화번호 인증 여부에 따라 표시 처리
        String phoneDisplay = member.getPhoneVerified()
                ? member.getPhoneNum()
                : "미인증";

        return MemberResponseDTO.MyPageDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneDisplay(phoneDisplay)
                .point(member.getPoint())
                .gender(member.getGender())
                .address(member.getAddress())
                .build();
    }
}