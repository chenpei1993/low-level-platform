package com.jenschen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jenschen.enumeration.question.QuestionTypeEnum;
import com.jenschen.typehandler.ListTypeHandler;
import lombok.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "question", autoResultMap = true)
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

    /**
     * 问题选项
     */
    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = ListTypeHandler.class)
    private List<String> options;
}
