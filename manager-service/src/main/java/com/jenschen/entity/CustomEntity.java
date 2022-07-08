package com.jenschen.entity;

import lombok.Data;

@Data
public class CustomEntity extends BaseEntity{
    private String name;
    private String phone;
    private String email;
    private Long[] tags;
}
