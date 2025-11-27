package com.example.spring_boot_a.domain.entity.user.dto;

import com.example.spring_boot_a.domain.entity.enums.AddressGu;
import com.example.spring_boot_a.domain.entity.enums.Gender;
import com.example.spring_boot_a.domain.entity.enums.SocialLoginType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class UserRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create {

        @NotBlank(message = "이름은 필수값입니다.")
        private String name;

        @NotNull(message = "성별은 필수값입니다.")
        private Gender gender;

        private LocalDate birth;

        @NotNull(message = "주소(구)는 필수값입니다.")
        private AddressGu address;

        @NotBlank(message = "이메일은 필수값입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "전화번호는 필수값입니다.")
        @Size(max = 20, message = "전화번호는 최대 20자입니다.")
        private String phoneNumber;

        @NotNull(message = "소셜 타입은 필수값입니다.")
        private SocialLoginType socialType;
    }
}
