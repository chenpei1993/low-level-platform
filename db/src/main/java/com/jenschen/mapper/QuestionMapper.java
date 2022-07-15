package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionEntity> {
    /**
     * 根据info id 删除对应的问卷
     * @param questionEntity 主要是info id
     */
    void deleteByInfoId(QuestionEntity questionEntity);
}
