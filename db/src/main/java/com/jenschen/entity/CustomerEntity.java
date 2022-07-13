package com.jenschen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("customer")
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends BaseEntity{

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱号
     */
    private String email;

    /**
     * 用户标签
     */
    private String tags;
}
