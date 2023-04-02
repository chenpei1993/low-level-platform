package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.user.UserLoginReq;
import com.jenschen.request.user.UserPageReq;
import com.jenschen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Response<Object> login(@RequestBody @Validated UserLoginReq userLoginReq){
        return userService.login(userLoginReq);
    }


    @DeleteMapping("logout")
    public Response<Object> logout(@RequestBody @Validated UserLoginReq userLoginReq){
        return userService.logout(userLoginReq);
    }

    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody UserPageReq userPageReq){
        return userService.page(userPageReq);
    }
}
