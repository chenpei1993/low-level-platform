package com.jenschen.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
