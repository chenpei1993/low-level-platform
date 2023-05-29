package com.jenschen.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerPageReq extends Page{

    private Integer infoId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
