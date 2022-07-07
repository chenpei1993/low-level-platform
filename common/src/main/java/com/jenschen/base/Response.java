package com.jenschen.base;


import java.util.List;

public class Response<T> {
    private Integer code;
    private String msg;
    private List<T> data;

    public Response() {
        this(200, "successful", null);
    }

    public Response(List<T> data) {
        this(200, "successful", data);
    }

    public Response(Integer code, String msg, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
