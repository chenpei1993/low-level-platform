package com.jenschen.elastic.entity;

import com.jenschen.elastic.convert.LocalDateTimeConverter;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.Date;
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

    @Field(type = FieldType.Date)
    @ValueConverter(LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date)
    @ValueConverter(LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;
}
