package com.jenschen.request;

import lombok.Data;

@Data
public class CustomReq {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long[] tags;
}
