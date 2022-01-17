package com.minhphuc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// Defines the logic to validate a given constraint A for a given object type T.
public class PasswordConstraintValidator implements ConstraintValidator<validPassword, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

}
