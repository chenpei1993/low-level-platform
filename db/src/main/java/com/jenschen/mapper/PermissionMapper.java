package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.PermissionEntity;
import com.jenschen.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.security.Permission;


@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntity> {

}