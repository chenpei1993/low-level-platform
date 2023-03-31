package com.jenschen.service;

import com.jenschen.base.Response;
import com.jenschen.request.UserLoginReq;

public interface UserService {

    /**
     * 管理后台的用户登录
     * @param userLoginReq 用户登录信息
     * @return
     */
    Response<Object> login(UserLoginReq userLoginReq);

    /**
     * 管理后台的用户注销
     * @param userLoginReq 用户登录信息
     * @return
     */
    Response<Object> logout(UserLoginReq userLoginReq);
}
