package com.jenschen.entity;


import lombok.Data;

@Data
public class TagEntity extends BaseEntity {

    private String name;
    private String group;
    private String color;

}
