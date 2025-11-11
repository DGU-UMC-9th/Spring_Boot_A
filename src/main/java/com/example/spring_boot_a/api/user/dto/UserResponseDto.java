package com.example.spring_boot_a.api.user.dto;

import com.example.spring_boot_a.domain.entity.enums.AddressGu;
import com.example.spring_boot_a.domain.entity.enums.Gender;
import com.example.spring_boot_a.domain.entity.enums.SocialLoginType;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

public class UserResponseDto {

    @Getter
    @Builder
    public static class CreateResult {
        private Long userId;
        private String name;
        private String email;
    }

    @Getter
    @Builder
    public static class Detail {
        private Long userId;
        private String name;
        private Gender gender;
        private LocalDate birth;
        private AddressGu address;
        private String email;
        private String phoneNumber;
        private Integer point;
        private SocialLoginType socialType;
        private Instant updatedAt;
    }

    @Getter
    @Builder
    public static class Summary {
        private Long userId;
        private String name;
        private AddressGu address;
        private Integer point;
    }
}
