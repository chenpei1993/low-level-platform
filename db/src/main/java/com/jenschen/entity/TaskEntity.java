package com.jenschen.entity;

import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.enumeration.TaskTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 任务
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskEntity extends BaseEntity{

    /**
     * 任务类型 参考 TaskTypeEnum
     */
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
     * 执行状态
     */
    private int status;

    /**
     * 发送类型
     */
    private SendTypeEnum sendType;

    /**
     * 发送消息
     */
    private String sendMessage;


}
