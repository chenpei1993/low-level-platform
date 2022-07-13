package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("customerTag")
@EqualsAndHashCode(callSuper = true)
public class CustomerTagEntity extends BaseEntity {

    /**
     * 主键ID
     */
    private int id;

    /**
     * 标签ID
     */
    private int tagId;

    /**
     * 客户ID
     */
    private int customerId;
}
