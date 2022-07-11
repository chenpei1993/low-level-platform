package com.jenschen.enumeration;

public enum SendTypeEnum implements BaseEnum{

    WECHAT(1, "微信"),
    EMAIL(2, "邮箱"),
    SMS(3, "短信");

    private final int value;
    private final String remark;

    SendTypeEnum(int value, String remark){
        this.value = value;
        this.remark = remark;
    }

    @Override
    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }
}
