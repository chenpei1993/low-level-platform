package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper extends BaseMapper<TaskEntity> {
    List<TaskEntity> getByInfoId(Integer type, Integer infoId);

    void deleteByInfoId (Integer type, Integer infoId, Integer operationId);
}