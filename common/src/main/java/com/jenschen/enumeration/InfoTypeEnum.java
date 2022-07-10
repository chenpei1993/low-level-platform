package com.jenschen.enumeration;

public enum InfoTypeEnum {
    QUESTION(1, "问卷");

    private final int type;
    private final String remark;

    InfoTypeEnum(int type, String remark){
        this.type = type;
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }
}
