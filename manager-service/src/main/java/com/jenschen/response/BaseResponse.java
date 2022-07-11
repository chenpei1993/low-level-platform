package com.jenschen.response;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResponse {
    private Date createdAt;
    private int createdBy;
    private Date updatedAt;
    private int updatedBy;
}
