package com.jenschen.enumeration;

public enum TaskStatusEnum implements BaseEnum{
    INIT(1, "初始化"),
    DOING(2, "执行中"),
    FINISH(2, "执行中");

    private final int value;
    private final String remark;

    TaskStatusEnum(int value, String remark){
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
