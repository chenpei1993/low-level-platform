package com.jenschen.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jenschen.enumeration.InfoTypeEnum;
import com.jenschen.enumeration.RepeatCollectTypeEnum;
import com.jenschen.enumeration.SendCustomerTypeEnum;
import com.jenschen.enumeration.SendTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InfoReq {
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
    private boolean isAutoSend;
    /**
     * 网页标题
     */
    private String title;
    /**
     * 活动开始时间
     */
    private LocalDateTime startDateTime;
    /**
     * 活动结束时间
     */
    private LocalDateTime endDateTime;

    /**
     * 一次活动的开始小时
     */
    private int beginHours;

    /**
     * 一次活动的开始分钟
     */
    private int beginMinutes;

    /**
     * 一次活动时长
     */
    private int total;
    /**
     * 发送的方式
     */
    private SendTypeEnum sendType;

    /**
     * 发送的时间
     */
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
     * 其他，备用
     */
    private String other;
}
