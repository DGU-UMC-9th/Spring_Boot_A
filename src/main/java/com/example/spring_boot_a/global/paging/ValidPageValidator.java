package com.example.spring_boot_a.global.paging;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPageValidator implements ConstraintValidator<ValidPage, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        // 숫자인지 확인
        if (!value.matches("^[0-9]+$")) {
            return false;
        }

        int page = Integer.parseInt(value);
        // 1 이상만 허용 (0, 음수는 에러)
        return page >= 1;
    }
}
