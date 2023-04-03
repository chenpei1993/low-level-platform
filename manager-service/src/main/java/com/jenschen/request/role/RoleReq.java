package com.jenschen.request.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleReq {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 角色名
     */
    @NotBlank(message = "权限名不能为空")
    private String name;

}
