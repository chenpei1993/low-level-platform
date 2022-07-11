package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.CustomReq;
import com.jenschen.entity.CustomerEntity;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    @PutMapping
    public Response<Object> add(CustomReq customDTO) {
        CustomerEntity customEntity = new CustomerEntity();
        BeanUtils.copyProperties(customDTO, customEntity);
        Date now = new Date();
        customEntity.created(now, 1);
        return ResultUtil.success();
    }

    @PostMapping
    public Response<Object> edit(CustomReq customDTO) {
        CustomerEntity customEntity = new CustomerEntity();
        BeanUtils.copyProperties(customDTO, customEntity);
        Date now = new Date();
        customEntity.created(now, 1);
        return ResultUtil.success();
    }

    /**
     * 删除客户
     * @param id 客户id
     * @return 结果
     */
    @DeleteMapping
    public Response<Object> del(Integer id) {
        return ResultUtil.success();
    }

    /**
     * 标记用户
     * @return 结果
     */
    @PostMapping("tag")
    public Response<Object> tagCustomer(){
        return ResultUtil.success();
    }
}
