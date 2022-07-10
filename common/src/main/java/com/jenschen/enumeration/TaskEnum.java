package com.jenschen.enumeration;

public enum TaskEnum {
    INIT(1, "初始化"),
    DOING(2, "进行中"),
    FINISH(3, "已完成"),
    ERROR(4, "错误");

    private final int value;
    private final String remark;

    TaskEnum(int value, String remark){
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }
}
