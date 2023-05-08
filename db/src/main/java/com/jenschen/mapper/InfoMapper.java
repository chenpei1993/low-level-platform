package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.InfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoMapper extends BaseMapper<InfoEntity> {
    InfoEntity getByCode(String code);
}