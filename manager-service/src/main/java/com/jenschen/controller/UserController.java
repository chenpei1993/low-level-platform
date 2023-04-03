package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.role.RoleReq;
import com.jenschen.request.user.UserLoginReq;
import com.jenschen.request.user.UserPageReq;
import com.jenschen.request.user.UserReq;
import com.jenschen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

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

    @PutMapping
    public Response<Object> add(@RequestBody @Validated UserReq userReq){
        return userService.add(userReq);
    }

    @PostMapping
    public Response<Object> edit(@RequestBody @Validated UserReq userReq){
        return userService.edit(userReq);
    }

    /**
     * 根据 id 删除用户
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> del(@PathVariable Integer id){
        return userService.delete(id);
    }
}
