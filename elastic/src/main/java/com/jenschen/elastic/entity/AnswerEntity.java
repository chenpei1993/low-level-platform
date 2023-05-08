package com.jenschen.elastic.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@Builder
@Document(indexName = "info")
public class AnswerEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    private Integer infoId;

    private Integer questionId;

    private String answer;
}
