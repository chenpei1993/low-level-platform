package com.jenschen.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SendTimerPageReq extends Page{
    private Integer infoId;
}
