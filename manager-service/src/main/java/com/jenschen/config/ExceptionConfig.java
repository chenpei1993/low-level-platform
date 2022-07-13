package com.jenschen.config;

import com.jenschen.base.Response;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.util.ResultUtil;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Object> methodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        return ResultUtil.error(ErrorEnum.INVALID_PARAMS, allErrors.get(0).getDefaultMessage());
    }
}
