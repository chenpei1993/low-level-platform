package com.jenschen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<TaskEntity> {

}