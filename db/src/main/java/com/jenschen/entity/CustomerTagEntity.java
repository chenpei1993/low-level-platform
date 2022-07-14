package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@TableName("customer_tag")
@EqualsAndHashCode(callSuper = true)
public class CustomerTagEntity extends BaseEntity {


    /**
     * 标签ID
     */
    private int tagId;

    /**
     * 客户ID
     */
    private int customerId;
}
