package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.SubInfoEntity;
import com.jenschen.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
