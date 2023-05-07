package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@TableName("setting")
@EqualsAndHashCode(callSuper = true)
public class SettingEntity extends BaseEntity {

    /**
     * 名
     */
    private String name;

    /**
     * 键
     */
    @TableField("`key`")
    private String key;

    /**
     * 值
     */
    private String value;
}
