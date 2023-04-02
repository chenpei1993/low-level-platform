package com.jenschen.request.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserReq {

    /**
     * 主键ID
     */
    @NotNull(message = "id不能为空")
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户不能为空")
    private String name;

    /**
     * 权限ID
     */
    private String roleIds;

}
