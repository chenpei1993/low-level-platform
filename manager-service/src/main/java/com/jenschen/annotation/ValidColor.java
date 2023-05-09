package com.jenschen.annotation;

import com.jenschen.annotation.validator.ColorValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER, TYPE})
@Constraint(validatedBy = ColorValidator.class)
public @interface ValidColor {
    String message() default "颜色代码不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


