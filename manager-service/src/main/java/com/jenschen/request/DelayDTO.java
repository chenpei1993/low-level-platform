package com.jenschen.request;

import lombok.Data;

@Data
public class DelayDTO{
    private int value;
    private String timeUnit;
    private int tipType;
    private String msg;
}
