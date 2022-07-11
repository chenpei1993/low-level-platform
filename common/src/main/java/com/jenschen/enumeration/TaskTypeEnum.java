package com.jenschen.enumeration;

public enum TaskTypeEnum implements BaseEnum{
    SEND(1, "推送任务"),
    DELAY(2, "延时任务");

    private final int value;
    private final String remark;

    TaskTypeEnum(int value, String remark){
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
