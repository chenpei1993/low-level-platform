package com.jenschen.annotation.validator;


import com.jenschen.annotation.ValidPhoneNumber;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final Pattern pattern;

    static {
        pattern = Pattern.compile("^[0123456789]{11}$");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(s)){
            return false;
        }

        return pattern.matcher(s).find();
    }

}
