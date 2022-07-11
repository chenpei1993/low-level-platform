package com.jenschen.enumeration;

public enum InfoTypeEnum implements BaseEnum{
    QUESTION(1, "问卷");

    private final int value;
    private final String remark;

    InfoTypeEnum(int type, String remark){
        this.value = type;
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
