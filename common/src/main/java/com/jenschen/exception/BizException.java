package com.jenschen.exception;

import com.jenschen.enumeration.ErrorEnum;

public class BizException extends RuntimeException{
    public BizException(String msg){
        super(msg);
    }

    public BizException(ErrorEnum errorEnum){
        super(errorEnum.getMessage());
    }
}
