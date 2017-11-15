package io.mirko.entity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FirstCapStringValidator implements ConstraintValidator<FirstCapString, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }
        final String[] pieces = value.split(" +");
        return Arrays.stream(pieces).allMatch(this::isFirstCap);
    }

    private boolean isFirstCap(String s) {
        final String s1 = s.substring(1);
        return Character.isUpperCase(s.charAt(0)) && s1.toLowerCase().equals(s1);
    }

    @Override
    public void initialize(FirstCapString constraintAnnotation) {

    }
}
