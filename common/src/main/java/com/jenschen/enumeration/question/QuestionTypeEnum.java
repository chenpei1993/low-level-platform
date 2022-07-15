package com.jenschen.enumeration.question;

import com.jenschen.enumeration.BaseEnum;

public enum QuestionTypeEnum implements BaseEnum {

    QUESTION(0, "简答题"),
    SINGLE_CHECKBOX(1, "单选题"),
    MULTI_CHECKBOX(2, "多选题"),
    UPLOAD(2, "上传");

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
