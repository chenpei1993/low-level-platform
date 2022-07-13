package com.jenschen.annotation.validator;


import com.jenschen.annotation.ValidColor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidColor, String> {

    private static final Pattern pattern;

    static {
        pattern = Pattern.compile("^[a-zA-Z0-9_\\.]+@[a-zA-Z0-9-]+[\\.a-zA-Z]+$");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(s)){
            return false;
        }

        return pattern.matcher(s).find();
    }

}
