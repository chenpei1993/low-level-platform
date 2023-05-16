package com.jenschen.request.custom;

import com.jenschen.annotation.ValidEmail;
import com.jenschen.annotation.ValidPhoneNumber;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerReq {

    /**
     * 唯一主键
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "客户姓名不能为空")
    private String name;

    /**
     * 手机号
     */
    @ValidPhoneNumber
    private String phone;

    /**
     * 邮箱
     */
    @ValidEmail
    private String email;

    /**
     * 标签组
     */
    private int[] tags;
}
