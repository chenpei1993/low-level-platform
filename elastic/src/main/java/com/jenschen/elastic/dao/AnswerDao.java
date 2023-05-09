package com.jenschen.elastic.dao;

import com.jenschen.elastic.entity.AnswerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AnswerDao extends ElasticsearchRepository<AnswerEntity, String> {

    Page<AnswerEntity> findByInfoId(Integer infoId, Pageable pageable);


    List<AnswerEntity> findByInfoIdAndCreatedAtBetween(Integer infoId, LocalDateTime from, LocalDateTime to);
}
