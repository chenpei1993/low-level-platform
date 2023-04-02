package com.jenschen.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionResp extends BaseResponse {

    /**
     * 唯一键
     */
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限
     */
    private String permission;

}
