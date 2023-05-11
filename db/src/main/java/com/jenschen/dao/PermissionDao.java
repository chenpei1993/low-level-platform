package com.jenschen.dao;



import com.jenschen.entity.PermissionEntity;

import java.util.List;

public interface PermissionDao extends BaseDao<PermissionEntity> {


    /**
     * 获取所有结果
     * @return 结果
     */
    List<PermissionEntity> all();

    /**
     * 根据角色ID获取权限代码
     * @param roleId 角色ID
     * @return 结果
     */
    List<PermissionEntity> getPermissionByRoleId(Integer roleId);

    /**
     * 根据角色IDS获取权限代码
     * @param roleIds 角色ID使用,号联合
     * @return 权限
     */
    List<PermissionEntity> getPermissionByRoleIds(List<String> roleIds);

}
