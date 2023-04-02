package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.Page;
import com.jenschen.request.permission.PermissionReq;

public interface PermissionService {

    /**
     * 分页获取标签
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);

    /**
     * 获取所有结果
     * @return 结果
     */
    Response<Object> all();

    /**
     * 根据角色获取ID
     * @param roleId 角色ID
     * @return 结果
     */
    Response<Object> getPermissionsByRoleId(Integer roleId);

    /**
     * 添加权限
     * @param permissionReq info id 和问题集合
     * @return 结果
     */
    Response<Object> add(PermissionReq permissionReq);

    /**
     * 根据 id 修改权限
     * @param permissionReq permissionReq
     * @return 结果
     */
    Response<Object> edit(PermissionReq permissionReq);

    /**
     * 根据id 删除权限
     * @param id 权限id
     * @return 结果
     */
    Response<Object> delete(Integer id);


}
