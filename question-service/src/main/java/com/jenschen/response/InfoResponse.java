package com.jenschen.response;

import lombok.Data;

import java.util.List;


@Data
public class InfoResponse {
    private Integer id;
    private String title;
    private String pageTitle;
    private String styleUrl;
    private List<QuestionResponse> questions;
}
