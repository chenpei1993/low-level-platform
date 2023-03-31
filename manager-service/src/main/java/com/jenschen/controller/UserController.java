package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.TagReq;
import com.jenschen.request.UserLoginReq;
import com.jenschen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Response<Object> login(@RequestBody @Validated UserLoginReq userLoginReq){
        return userService.login(userLoginReq);
    }


    @DeleteMapping
    public Response<Object> logout(@RequestBody @Validated UserLoginReq userLoginReq){
        return userService.logout(userLoginReq);
    }
}
