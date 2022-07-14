package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@TableName("tag")
@EqualsAndHashCode(callSuper = true)
public class TagEntity extends BaseEntity {

    /**
     * 标签名
     */
    private String name;

    /**
     * 颜色
     */
    private String color;

}
