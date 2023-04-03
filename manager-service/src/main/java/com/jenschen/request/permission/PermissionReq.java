package com.jenschen.request.permission;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PermissionReq {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 权限名
     */
    @NotBlank(message = "权限名不能为空")
    private String name;

    /**
     * 权限代码
     */
    @NotBlank(message = "权限代码不能为空")
    private String permission;
}
