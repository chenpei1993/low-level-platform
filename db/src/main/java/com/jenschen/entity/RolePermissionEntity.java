package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermissionEntity extends BaseEntity {

    /**
     * 角色ID
     */
    private Integer role_id;

    /**
     * 权限ID
     */
    private Integer permission_id;
}
