package com.jenschen.response;


import com.jenschen.enumeration.question.QuestionTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private Integer idx;
    private QuestionTypeEnum type;
    private boolean isRequire;
    private String questionDesc;
    private List<String> options;
}
