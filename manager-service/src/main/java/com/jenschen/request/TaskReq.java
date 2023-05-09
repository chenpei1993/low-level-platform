package com.jenschen.request;

import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.enumeration.TimeUnitEnum;
import lombok.Data;


@Data
public class TaskReq {
    private int value;
    private TimeUnitEnum timeUnit;
    private SendTypeEnum tipType;
    private String repeatValue;
    private String message;
}
