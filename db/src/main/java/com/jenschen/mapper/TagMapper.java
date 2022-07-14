package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TagMapper extends BaseMapper<TagEntity> {

}