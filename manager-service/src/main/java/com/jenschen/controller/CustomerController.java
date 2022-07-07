package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.util.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    /**
     * 标记用户
     * @return 结果
     */
    @PostMapping
    public Response<Object> tagCustomer(){
        return ResultUtil.success();
    }
}
