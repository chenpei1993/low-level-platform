package com.jenschen.response;

import com.jenschen.entity.RoleEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserResp extends BaseResponse {

    /**
     * 唯一键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 权限列表
     */
    private List<RoleResp> roleIds;
}
