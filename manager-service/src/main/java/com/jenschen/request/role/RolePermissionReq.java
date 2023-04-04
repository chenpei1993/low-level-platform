package com.jenschen.request.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RolePermissionReq {

    /**
     * 主键ID
     */
    @NotNull( message = "角色ID缺失")
    private Integer roleId;

    /**
     * 权限代码code
     */
    private List<Integer> permissionIds;
}
