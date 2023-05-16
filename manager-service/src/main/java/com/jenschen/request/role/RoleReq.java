package com.jenschen.request.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleReq {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 角色名
     */
    @NotBlank(message = "角色名不能为空")
    private String name;
}
