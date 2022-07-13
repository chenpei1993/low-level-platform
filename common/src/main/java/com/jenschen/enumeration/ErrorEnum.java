package com.jenschen.enumeration;

public enum ErrorEnum {
    INFO_ERROR_REPEATCOLLECTTYPE(10001, "错误的重复类型"),
    INVALID_PARAMS(20001, "错误的重复类型");


    private int code;
    private String message;

    ErrorEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
