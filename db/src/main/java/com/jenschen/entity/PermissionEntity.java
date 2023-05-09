package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("permission")
@EqualsAndHashCode(callSuper = true)
public class PermissionEntity extends BaseEntity {

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限代码
     */
    private String permission;

}
