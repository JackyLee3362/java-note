package edu.note.validation;

import javax.validation.ConstraintValidator;

public class PasswordMatchValidator implements ConstraintValidator<ValidPassword, String> {

        @Override
        public boolean isValid(String value, javax.validation.ConstraintValidatorContext context) {
            if (value == null) {
                return true; // 允许 null 值，使用 @NotNull 来校验非空
            }
            boolean hasUppercase = value.chars().anyMatch(Character::isUpperCase);
            boolean hasLowercase = value.chars().anyMatch(Character::isLowerCase);
            boolean hasDigit = value.chars().anyMatch(Character::isDigit);
            return hasUppercase && hasLowercase && hasDigit;
        }
    }
