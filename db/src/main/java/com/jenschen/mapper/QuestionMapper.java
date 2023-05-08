package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionEntity> {
    /**
     * 根据info id 删除对应的问卷
     * @param questionEntity 主要是info id
     */
    void deleteByInfoId(QuestionEntity questionEntity);


    /**
     * 根据地址码获取答题
     */
    List<QuestionEntity> getByCode(String code);
}
