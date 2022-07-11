package com.jenschen.annotation.validator;


import com.jenschen.annotation.ValidColor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ColorValidator implements ConstraintValidator<ValidColor, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(s)){
            return false;
        }

        String pattern = "/#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})/g";
        return Pattern.matches(pattern, s);
    }
}
