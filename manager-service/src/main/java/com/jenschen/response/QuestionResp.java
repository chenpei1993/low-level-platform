package com.jenschen.response;

import com.jenschen.enumeration.question.QuestionTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionResp extends BaseResponse{

    /**
     * 问题顺序
     */
    private int idx;

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
    private String questionDesc;

    /**
     * 问题选项
     */
    private List<String> options;
}
