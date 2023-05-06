package com.jenschen.enumeration.question;

import com.jenschen.enumeration.BaseEnum;

public enum QuestionTypeEnum implements BaseEnum {


    QUESTION(1, "简答题"),
    SINGLE_CHECKBOX(2, "单选题"),
    MULTI_CHECKBOX(3, "多选题"),
    UPLOAD(4, "上传");

    private final int value;
    private final String remark;

    QuestionTypeEnum(int type, String remark){
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
