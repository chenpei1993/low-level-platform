package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.Page;
import com.jenschen.request.user.UserLoginReq;
import com.jenschen.request.user.UserReq;

public interface UserService {

    /**
     * 管理后台的用户登录
     * @param userLoginReq 用户登录信息
     * @return
     */
    Response<Object> login(UserLoginReq userLoginReq);

    /**
     * 管理后台的用户注销
     *
     * @return
     */
    Response<Object> logout();

    /**
     * 分页获取用户
     * @param page 分页
     * @return 结果
     */
    Response<Object> page(Page page);

    /**
     * 添加用户
     * @param userReq 用户
     * @return 结果
     */
    Response<Object> add(UserReq userReq);

    /**
     * 根据 id 修改角色
     * @param userReq userReq
     * @return 结果
     */
    Response<Object> edit(UserReq userReq);

    /**
     * 根据id 删除用户
     * @param id 用户id
     * @return 结果
     */
    Response<Object> delete(Integer id);
}
