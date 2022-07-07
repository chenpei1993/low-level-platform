package com.jenschen.util;

import com.jenschen.base.Response;

import java.util.List;

public class ResultUtil {

    private ResultUtil(){};

    private static Response<Object> successObj = new Response<>();

    public static Response<Object> success(){
        return successObj;
    }

    public static <T> Response<T> success(List<T> data){
        return new Response<T>(data);
    }
}
