package com.jenschen.dao;

import com.jenschen.entity.PermissionEntity;
import com.jenschen.entity.QuestionEntity;

import java.util.List;

public interface QuestionDao extends BaseDao<QuestionEntity> {

    List<QuestionEntity> getByInfoId(Integer id);

    List<QuestionEntity> getByCode(String code);

    void deleteByInfoId(QuestionEntity questionEntity);
}
