package com.jenschen.response;


import lombok.Data;

@Data
public class QuestionResponse {
    private Long id;
    private Integer idx;
    private Integer type;
    private boolean isRequire;
    private String questionDesc;
}
