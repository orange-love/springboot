package com.fruitday.boot.config.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DemoValidator implements ConstraintValidator<DemoValid,String> {

    DemoValid demoValid;
    int max;
    @Override
    public void initialize(DemoValid constraintAnnotation) {
        this.demoValid = constraintAnnotation;
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.length() < max;
    }
}
