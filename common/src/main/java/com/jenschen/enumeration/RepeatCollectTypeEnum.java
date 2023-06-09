package com.jenschen.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jenschen.util.EnumUtil;

public enum RepeatCollectTypeEnum implements BaseEnum{
    ONCE(1, "一次"),
    EVERY_DAY(10, "每日"),
    EVERY_WEEK(100, "每周"),
    EVERY_MONTH(1000, "每月");


    private final int value;
    private final String remark;

    RepeatCollectTypeEnum(int value, String remark){
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
    public static RepeatCollectTypeEnum getByValue(int value){
        return EnumUtil.valueOf(RepeatCollectTypeEnum.class, value);
    }

}
