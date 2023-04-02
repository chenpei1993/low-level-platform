package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.Page;
import com.jenschen.request.role.RoleReq;

public interface RoleService {

    /**
     * 分页获取标签
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);

    /**
     * 添加橘色
     * @param roleReq info id 和问题集合
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
     * @param id 权限id
     * @return 结果
     */
    Response<Object> delete(Integer id);


}
