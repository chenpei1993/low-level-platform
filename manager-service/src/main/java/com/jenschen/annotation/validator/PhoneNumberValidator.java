package com.jenschen.annotation.validator;


import com.jenschen.annotation.ValidColor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidColor, String> {

    private static final Pattern pattern;

    static {
        pattern = Pattern.compile("^1[3456789]\\d{9}$/、/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(s)){
            return false;
        }

        return pattern.matcher(s).find();
    }

}
