package com.jenschen.request;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequest {
    private Integer infoId;
    private List<Answer> answers;
}
