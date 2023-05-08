package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role")
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity {

    /**
     * 角色名
     */
    private String name;
}
