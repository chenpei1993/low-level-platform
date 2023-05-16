package com.jenschen.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jenschen.enumeration.InfoTypeEnum;
import com.jenschen.enumeration.RepeatCollectTypeEnum;
import com.jenschen.enumeration.SendCustomerTypeEnum;
import com.jenschen.enumeration.SendTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InfoReq{
    /**
     * ID
     */
    private Integer id;
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
    private String repeatValue;

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
     * 推送开始小时
     */
    private int beginHours;

    /**
     * 推送开始分钟
     */
    private int beginMinutes;

    /**
     * 发送的方式
     */
    private SendTypeEnum sendType;

    /**
     * 推送的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sendDateTime;

    /**
     * 发送的消息模板
     */
    private String sendMessage;
    /**
     * 发送的客户类型
     */
    private SendCustomerTypeEnum sendCustomerType;

    /**
     * 发送的客户
     */
    private String sendCustomers;

    /**
     * 延时提醒任务
     */
    private List<TaskReq> delayTipTimers;

    /**
     * 状态
     */
    private int status;

    /**
     * 其他，备用
     */
    private String other;

    /**
     * 版本号
     */
    private int version;
}
