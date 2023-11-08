package com.starter_kits_usmb.back_java_spring_boot.auth;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoBannedCharsValidator.class)
@Documented
public @interface NoBannedChars {
    String message() default "Contains banned characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    char[] bannedChars() default {' '}; // Default to space character
}
