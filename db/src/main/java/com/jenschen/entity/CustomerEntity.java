package com.jenschen.entity;

import lombok.Data;

@Data
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
