package com.jenschen.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jenschen.enumeration.InfoTypeEnum;
import com.jenschen.enumeration.RepeatCollectTypeEnum;
import com.jenschen.enumeration.SendTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class InfoResp extends BaseResponse {
    /**
     * 主键Id
     */
    private int id;
    /**
     * 活动名
     */
    private String name;

    /**
     * 活动类型
     */
    private InfoTypeEnum type;

    /**
     * 重复收集类型
     */
    private RepeatCollectTypeEnum repeatCollectType;

    /**
     * 针对每周，每月，后面的数值，可以，分割
     */
    private int[] repeatValue;

    /**
     * 定时发送
     */
    private boolean autoSend;
    /**
     * 网页标题
     */
    private String title;
    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startDateTime;
    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endDateTime;

    /**
     * 发送的方式
     */
    private SendTypeEnum sendType;


    /**
     * 发送的消息模板
     */
    private String sendMessage;

    /**
     * 发送的客户
     */
    private String sendCustomers;

    /**
     * URL
     */
    private String url;

    /**
     * 状态
     */
    private int status;

    /**
     * 其他，备用
     */
    private String other;

    private List<TaskResp> tipTasks;

    private List<TaskResp> sendTasks;
}
