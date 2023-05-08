package com.jenschen.elastic.dao;

import com.jenschen.elastic.entity.AnswerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AnswerDao extends ElasticsearchRepository<AnswerEntity, String> {

//    Page<AnswerEntity> findByInfoId(Integer infoId, Pageable pageable);
}
