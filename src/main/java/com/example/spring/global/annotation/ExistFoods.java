package com.example.spring.global.annotation;

import com.example.spring.global.validator.FoodExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// 사용자 정의 어노테이션을 만들기 위해
@Documented
// 커스텀 어노테이션을 통해 validation을 수행하도록 지정
@Constraint(validatedBy = FoodExistValidator.class)
// 이 어노테이션이 적용될 수 있는 대상 지정
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
// 이 어노테이션의 생명주기를 지정: 런타임까지 유지되도록 설정
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistFoods {
    //여기서 디폴트 메시지를 설정합니다.
    String message() default "해당 음식이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}