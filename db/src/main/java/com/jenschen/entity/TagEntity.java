package com.jenschen.entity;


import lombok.Data;

@Data
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
