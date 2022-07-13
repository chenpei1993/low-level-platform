package com.jenschen.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jenschen.base.Response;
import com.jenschen.dao.CustomerMapper;
import com.jenschen.request.CustomReq;
import com.jenschen.entity.CustomerEntity;
import com.jenschen.request.CustomerPageReq;
import com.jenschen.request.TagPageReq;
import com.jenschen.service.CustomerService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PutMapping
    public Response<Object> add(CustomReq customDTO) {
        return customerService.insert(customDTO);
    }

    @PostMapping
    public Response<Object> edit(CustomReq customReq) {
        return customerService.updated(customReq);
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

    /**
     * 分页
     * @param customerPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody CustomerPageReq customerPageReq){
        return customerService.page(customerPageReq);
    }
}
