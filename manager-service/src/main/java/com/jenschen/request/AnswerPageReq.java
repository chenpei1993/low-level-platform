package com.jenschen.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerPageReq extends Page{
    private Integer infoId;
}
