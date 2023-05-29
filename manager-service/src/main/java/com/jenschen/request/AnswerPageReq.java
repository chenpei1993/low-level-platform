package com.jenschen.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerPageReq extends Page{

    @NotBlank(message = "问卷ID不能为空")
    private Integer infoId;

    @NotBlank(message = "开始ß时间不能空")
    private LocalDateTime startDate;

    @NotBlank(message = "结束时间不能空")
    private LocalDateTime endDate;

}
