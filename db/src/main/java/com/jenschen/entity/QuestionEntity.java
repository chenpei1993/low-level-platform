package com.jenschen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jenschen.enumeration.question.QuestionTypeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@TableName("question")
@EqualsAndHashCode(callSuper = true)
public class QuestionEntity extends BaseEntity{

    /**
     * 关联的info id
     */
    private int infoId;

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
}
