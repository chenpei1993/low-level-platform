package com.jenschen.util;

import com.jenschen.base.Response;
import com.jenschen.enumeration.ErrorEnum;

import java.util.List;

public class ResultUtil {

    private ResultUtil(){}

    private static Response<Object> successObj = new Response<>();

    public static Response<Object> success(){
        return successObj;
    }

    public static <T> Response<T> success(T data){
        return new Response<T>(data);
    }

    public static <T> Response<T> error(ErrorEnum errorEnum){
        return new Response<T>(errorEnum);
    }

    public static <T> Response<T> error(ErrorEnum errorEnum, String message){
        return new Response<T>(errorEnum, message);
    }
}
