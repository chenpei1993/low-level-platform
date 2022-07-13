package com.jenschen.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jenschen.util.EnumUtil;

public enum SendCustomerTypeEnum implements BaseEnum{
    SELF_DEFINITION(1, "自定义"),
    TAG(2, "标签");


    private final int value;
    private final String remark;

    SendCustomerTypeEnum(int value, String remark){
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
    public static SendCustomerTypeEnum getByValue(int value){
        return EnumUtil.valueOf(SendCustomerTypeEnum.class, value);
    }
}
