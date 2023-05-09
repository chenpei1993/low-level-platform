package com.jenschen.enumeration;

public enum TimeUnitEnum implements BaseEnum{
    MINUTES(1, "分钟"),
    HOURS(2, "时"),
    DAYS(3, "天");

    private final int value;
    private final String remark;

    TimeUnitEnum(int value, String remark){
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
