package com.jenschen.request;

import lombok.Data;

@Data
public class TaskDTO {
    private int value;
    private String timeUnit;
    private int tipType;
    private String msg;
}
