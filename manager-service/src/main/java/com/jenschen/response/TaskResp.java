package com.jenschen.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.enumeration.TaskStatusEnum;
import com.jenschen.enumeration.TaskTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TaskResp extends BaseResponse {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime executionDateTime;

    /**
     * 发送方式
     */
    private SendTypeEnum sendType;

    /**
     * 发送消息
     */
    private String sendMessage;

    /**
     * 执行状态
     */
    private TaskStatusEnum status;
}
