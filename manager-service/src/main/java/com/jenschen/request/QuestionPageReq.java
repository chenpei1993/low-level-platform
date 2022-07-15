package com.jenschen.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionPageReq extends Page{

    /**
     * 活动ID
     */
    private int infoId;
}
