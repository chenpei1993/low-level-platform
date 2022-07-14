package com.jenschen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.enumeration.TaskStatusEnum;
import com.jenschen.enumeration.TaskTypeEnum;
import com.jenschen.typehandler.ValueEnumTypeHandler;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 任务
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class TaskEntity extends BaseEntity{

    /**
     * 任务类型 参考 TaskTypeEnum
     */
    @TableField(typeHandler = ValueEnumTypeHandler.class)
    private TaskTypeEnum type;

    /**
     * 关联的活动信息ID
     */
    private int infoId;

    /**
     * 执行时间
     */
    private LocalDateTime executionDatetime;

    /**
     * 发送方式
     */
    @TableField(typeHandler = ValueEnumTypeHandler.class)
    private SendTypeEnum sendType;

    /**
     * 发送消息
     */
    private String sendMessage;

    /**
     * 执行状态
     */
    @TableField(typeHandler = ValueEnumTypeHandler.class)
    private TaskStatusEnum status;


}
