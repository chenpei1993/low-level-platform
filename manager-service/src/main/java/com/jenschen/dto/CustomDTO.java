package com.jenschen.dto;

import lombok.Data;

@Data
public class CustomDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long[] tags;
}
