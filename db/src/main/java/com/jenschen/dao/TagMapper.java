package com.jenschen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {

}