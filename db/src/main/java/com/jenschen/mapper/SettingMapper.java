package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.jenschen.entity.SettingEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingMapper extends BaseMapper<SettingEntity> {
}
