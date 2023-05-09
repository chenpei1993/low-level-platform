package com.jenschen.enumeration;

public enum InfoStatusEnum implements BaseEnum{
    UNKOWN(0, "未知"),
    EDIT(1, "可编辑"),
    PUBLISH(2, "已发布"),
    STOP(2, "已停用");

    private final int value;
    private final String remark;

    InfoStatusEnum(int type, String remark){
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

    public static InfoStatusEnum getNextStatus(InfoStatusEnum statusEnum){
        if(EDIT.equals(statusEnum)){
            return PUBLISH;
        }

        if(PUBLISH.equals(statusEnum)){
            return STOP;
        }

        if(STOP.equals(statusEnum)){
            return EDIT;
        }

        return UNKOWN;
    }
}
