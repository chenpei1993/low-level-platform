package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.role.RolePageReq;
import com.jenschen.request.role.RoleReq;
import com.jenschen.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制器
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    /**
     * 分页
     * @param rolePageReq 分页以及需要过滤的数据
     * @return 结果
     */
    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody RolePageReq rolePageReq){
        return roleService.page(rolePageReq);
    }

    /**
     * 获取所有角色
     * @return 结果
     */
    @GetMapping(value = "/all")
    @ResponseBody
    public Response<Object> getAllRole(){
        return roleService.all();
    }


    @PutMapping
    public Response<Object> add(@RequestBody @Validated RoleReq roleReq){
        return roleService.add(roleReq);
    }

    @PostMapping
    public Response<Object> edit(@RequestBody @Validated RoleReq roleReq){
        return roleService.edit(roleReq);
    }

    /**
     * 根据 id 删除角色
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> del(@PathVariable Integer id){
        return roleService.delete(id);
    }


}