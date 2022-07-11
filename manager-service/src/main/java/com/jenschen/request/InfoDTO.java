package com.jenschen.request;

import com.jenschen.enumeration.RepeatCollectTypeEnum;
import com.jenschen.enumeration.SendTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InfoDTO {
    private String name;
    private int type;
    private RepeatCollectTypeEnum repeatCollectType;
    private boolean isAutoSend;
    private String title;
    private Date startDateTime;
    private Date endDateTime;
    private Date sendDate;
    private SendTypeEnum sendType;
    private String sendMessage;
    private List<TaskDTO> delayTipTimers;
    private String other;
}
