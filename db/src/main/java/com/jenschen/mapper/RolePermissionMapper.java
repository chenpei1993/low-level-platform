package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<PermissionEntity> {

    /**
     * 根据角色ID 查找所有的权限集合
     * @param roleId 角色I
     * @return 角色的权限集合
     */
    List<PermissionEntity> getPermissionByRoleId(int roleId);
}
