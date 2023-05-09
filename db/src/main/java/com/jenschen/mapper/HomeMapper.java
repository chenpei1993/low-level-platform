package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.HomeEntity;
import com.jenschen.entity.InfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper  extends BaseMapper<InfoEntity> {
    HomeEntity getBasicHomeInfo(String username);
}
