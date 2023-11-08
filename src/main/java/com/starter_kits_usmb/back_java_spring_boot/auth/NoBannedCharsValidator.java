package com.starter_kits_usmb.back_java_spring_boot.auth;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoBannedCharsValidator implements ConstraintValidator<NoBannedChars, String> {
    private char[] bannedChars;

    @Override
    public void initialize(NoBannedChars constraint) {
        bannedChars = constraint.bannedChars();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are considered valid
        }

        for (char bannedChar : bannedChars) {
            if (value.indexOf(bannedChar) != -1) {
                return false; // Contains a banned character
            }
        }

        return true; // No banned characters found
    }
}
