package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.InfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoMapper extends BaseMapper<InfoEntity> {
    InfoEntity getByCode(String code);


    List<InfoEntity> getLastNInfo(Integer num);
}