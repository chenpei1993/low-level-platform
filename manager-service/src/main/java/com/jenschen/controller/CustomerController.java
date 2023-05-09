package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.CustomerReq;
import com.jenschen.request.CustomerPageReq;
import com.jenschen.service.CustomerService;
import com.jenschen.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("customer")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 新增用户
     * @param customerReq 用户信息
     * @return 结果
     */
    @PutMapping
    @PreAuthorize("hasAuthority('custom:add')")
    public Response<Object> add(@RequestBody @Validated CustomerReq customerReq) {
        return customerService.insert(customerReq);
    }

    /**
     * 编辑用户信息
     * @param customerReq 用户信息
     * @return 结果
     */
    @PostMapping
    @PreAuthorize("hasAuthority('custom:edit')")
    public Response<Object> edit(@RequestBody @Validated CustomerReq customerReq) {
        return customerService.update(customerReq);
    }

    /**
     * 删除客户
     * @param id 客户id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('custom:del')")
    public Response<Object> del(@PathVariable  int id) {
        return customerService.delete(id);
    }

    /**
     * 分页
     * @param customerPageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    @PreAuthorize("hasAuthority('custom:query')")
    public Response<Object> page(@RequestBody CustomerPageReq customerPageReq){
        return customerService.page(customerPageReq);
    }
}
