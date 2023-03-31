package com.jenschen.service.impl;

import com.jenschen.base.Response;
import com.jenschen.request.UserLoginReq;
import com.jenschen.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Response<Object> login(UserLoginReq userLoginReq) {
        return null;
    }

    @Override
    public Response<Object> logout(UserLoginReq userLoginReq) {
        return null;
    }
}
