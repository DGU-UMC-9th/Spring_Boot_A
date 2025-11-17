package com.example.demo.domain.member.dto;

import com.example.demo.domain.member.enums.Gender;
import com.example.demo.global.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageDTO {
        private String name;
        private String email;
        private String phoneDisplay;  // 인증 여부에 따라 처리
        private Integer point;
        private Gender gender;
        private Region address;
    }
}