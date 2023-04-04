package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.Page;
import com.jenschen.request.role.RolePermissionReq;
import com.jenschen.request.role.RoleReq;

public interface RoleService {

    /**
     * 分页获取角色
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);

    /**
     * 获取所有角色
     * @return 结果
     */
    Response<Object> all();

    /**
     * 添加角色
     * @param roleReq roleReq
     * @return 结果
     */
    Response<Object> add(RoleReq roleReq);

    /**
     * 根据 id 修改角色
     * @param roleReq roleReq
     * @return 结果
     */
    Response<Object> edit(RoleReq roleReq);

    /**
     * 根据id 删除角色
     * @param id 角色id
     * @return 结果
     */
    Response<Object> delete(Integer id);


    /**
     * 更新角色和权限代码的关联表
     * @param rolePermissionReq 角色 ID 和权限代码
     */
    Response<Object> updatePermissions(RolePermissionReq rolePermissionReq);

}
