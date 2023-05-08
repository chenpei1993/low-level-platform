package com.jenschen.dao;

import com.jenschen.entity.QuestionEntity;

import java.util.List;

public interface QuestionDao {

    List<QuestionEntity> getByInfoId(Integer id);

    List<QuestionEntity> getByCode(String code);
}
