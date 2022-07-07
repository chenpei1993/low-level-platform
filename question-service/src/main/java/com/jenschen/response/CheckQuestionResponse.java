package com.jenschen.response;

import lombok.Data;

import java.util.List;

@Data
public class CheckQuestionResponse extends QuestionResponse {
    private List<String> questionOptions;
}
