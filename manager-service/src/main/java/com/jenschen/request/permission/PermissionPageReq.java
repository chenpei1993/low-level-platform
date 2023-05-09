package com.jenschen.request.permission;

import com.jenschen.request.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionPageReq extends Page {
    /**
     * 权限名
     */
    private String name;

    /**
     * 权限
     */
    private String permission;
}
