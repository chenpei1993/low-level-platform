package com.jenschen.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jenschen.util.EnumUtil;

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

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static SendTypeEnum getByValue(int value){
        return EnumUtil.valueOf(SendTypeEnum.class, value);
    }
}
