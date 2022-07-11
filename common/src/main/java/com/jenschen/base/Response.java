package com.jenschen.base;


import com.jenschen.enumeration.ErrorEnum;

import java.util.List;

public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public Response() {
        this(200, "successful", null);
    }

    public Response(T data) {
        this(200, "successful", data);
    }

    public Response(ErrorEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMessage(), null);
    }

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
