package com.jenschen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jenschen.enumeration.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@TableName("sub_info")
@EqualsAndHashCode(callSuper = true)
public class SubInfoEntity extends BaseEntity{

    /**
     * 父id
     */
    private int parent_id;

    /**
     * 活动开始时间
     */
    private LocalDateTime startDateTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endDateTime;



}
