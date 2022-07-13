package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.CustomerReq;
import com.jenschen.request.CustomerPageReq;
import com.jenschen.service.CustomerService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PutMapping
    public Response<Object> add(CustomerReq customDTO) {
        return customerService.insert(customDTO);
    }

    @PostMapping
    public Response<Object> edit(CustomerReq customerReq) {
        return customerService.updated(customerReq);
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
