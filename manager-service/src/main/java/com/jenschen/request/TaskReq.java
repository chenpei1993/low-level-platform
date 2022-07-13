package com.jenschen.request;

import com.jenschen.enumeration.SendTypeEnum;
import lombok.Data;

@Data
public class TaskReq {
    private int value;
    private String timeUnit;
    private SendTypeEnum tipType;
    private String repeatValue;
    private String message;
}
