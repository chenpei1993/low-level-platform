package com.jenschen.enumeration;

public enum ErrorEnum {
    ERROR(50000, "程序出现错误"),
    CUSTOMER_DUPLICATE_PHONE_OR_EMAIL(30001, "重复的手机号或者邮箱"),
    INFO_ERROR_REPEATCOLLECTTYPE(20001, "错误的重复类型"),
    INFO_ERROR_SEND_DATE_TIME(20001, "定时发送时间应该在活动开始时间之后，活动结束时间之前"),
    INVALID_PARAMS(10001, "错误的重复类型"),
    DELETED_RECORD(10002, "数据已被删除"),

    ACCESS_DENIED(20001, "无访问权限"),

    ACTIVITY_NOT_EXIST(70001, "活动不存在"),
    ACTIVITY_NOT_BEGIN(70002, "活动未开始"),
    ACTIVITY_END(70003, "活动已结束"),

    QUESTION_REQUIRED(80001, "请回答必填问题");


    private final int code;
    private final String message;

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
