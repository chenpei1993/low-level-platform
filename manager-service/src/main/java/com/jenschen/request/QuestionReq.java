package com.jenschen.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QuestionReq{
    /**
     * 活动Id
     */
    @NotNull
    private Integer infoId;

    /**
     * 问题集合
     */
    private List<QuestionInfo> questionInfoList;
}
