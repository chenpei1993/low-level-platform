package com.jenschen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jenschen.enumeration.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 活动信息
 */

@Data
@TableName("info")
@EqualsAndHashCode(callSuper = true)
public class InfoEntity extends BaseEntity{
    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     * 参考 InfoTypeEnum
     */
    private int type;

    /**
     * 活动重复类型
     * RepeatCollectTypeEnum
     */
    private RepeatCollectTypeEnum repeatCollectType;

    /**
     * 针对每周，每月，后面的数值，可以，分割
     */
    private String repeatValue;

    /**
     * 是否开启定时发送功能
     */
    private boolean isAutoSend;

    /**
     * 页面标题
     */
    private String title;

    /**
     * 活动开始时间
     */
    private Date startDateTime;

    /**
     * 活动结束时间
     */
    private Date endDateTime;

    /**
     * 定时发送时间，如果repeatCollectType 是每天，每周，每月，则只取时间
     */
    private Date sendDateTime;

    /**
     * 定时任务发送方式
     */
    private SendTypeEnum sendType;

    /**
     * 发送的文本模板
     */
    private String sendMessage;

    /**
     * 发送的客户类型
     */
    private SendCustomerTypeEnum sendCustomerType;

    /**
     * 发送的客户, 标签或者手机号等等
     */
    private String sendCustomers;

    /**
     * 生成的url后缀
     */
    private String url;

    /**
     * 活动的状态
     */
    private InfoStatusEnum status;

    /**
     * 其他
     */
    private String other;
}
