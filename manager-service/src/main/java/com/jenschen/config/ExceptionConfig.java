package com.jenschen.config;

import com.jenschen.base.Response;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<Object> globalException(Exception e){
        log.error(e.getMessage(), e);
        return ResultUtil.error(ErrorEnum.ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Object> methodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        return ResultUtil.error(ErrorEnum.INVALID_PARAMS, allErrors.get(0).getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Response<Object> methodArgumentNotValidException(AccessDeniedException e){
        return ResultUtil.error(ErrorEnum.ACCESS_DENIED, e.getMessage());
    }
}
