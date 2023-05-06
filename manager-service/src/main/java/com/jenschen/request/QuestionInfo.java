package com.jenschen.request;

import com.jenschen.enumeration.question.QuestionTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QuestionInfo {

    /**
     * 题目顺序
     */
    private Integer idx;
    /**
     * 题目类型
     */
    private QuestionTypeEnum type;

    /**
     * 是否是必选题
     */
    private boolean isRequired;

    /**
     * 问题描述
     */
    @NotBlank(message = "问题描述不能空")
    private String questionDesc;
}
