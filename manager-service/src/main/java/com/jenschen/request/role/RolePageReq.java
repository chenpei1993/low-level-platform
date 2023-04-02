package com.jenschen.request.role;

import com.jenschen.request.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageReq extends Page {
    /**
     * 角色名
     */
    private String name;

}
