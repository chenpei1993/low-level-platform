package com.jenschen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.entity.PermissionEntity;
import com.jenschen.entity.RolePermissionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {

    /**
     * 根据角色ID 查找所有的权限集合
     * @param roleId 角色I
     * @return 角色的权限集合
     */
    List<PermissionEntity> getPermissionByRoleId(int roleId);

    /**
     * 根据角色IDS 查找所有的权限集合
     * @param roleIds 角色I
     * @return 角色的权限集合
     */
    List<PermissionEntity> getPermissionByRoleIds(List<String> roleIds);

    /**
     * 根据角色ID 删除所有的权限集合
     * @param roleId 角色ID
     * @return 结果
     */
    int deletePermissionByRoleId(int roleId, int operationId);


    /**
     * 根据用户ID获得所有权限代码
     * @param userId 用户ID
     * @return 权限代码
     */
    List<String> getPermissionByUserId(int userId);
}
