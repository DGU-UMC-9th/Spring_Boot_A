package com.umc.training.global.validator;

import com.umc.training.global.annotation.PageNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PageNumberValidator implements ConstraintValidator<PageNumber, Integer> {

    @Override
    public void initialize(PageNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        boolean isValid = value >= 0;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("페이지 번호는 0 이상이어야 합니다.")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
