package com.jenschen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity{

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
