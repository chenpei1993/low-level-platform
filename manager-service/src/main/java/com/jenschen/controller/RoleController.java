package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.role.RolePageReq;
import com.jenschen.request.role.RolePermissionReq;
import com.jenschen.request.role.RoleReq;
import com.jenschen.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('setting:role:query')")
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
    @PreAuthorize("hasAuthority('setting:role:add')")
    public Response<Object> add(@RequestBody @Validated RoleReq roleReq){
        return roleService.add(roleReq);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('setting:role:edit')")
    public Response<Object> edit(@RequestBody @Validated RoleReq roleReq){
        return roleService.edit(roleReq);
    }

    /**
     * 根据 id 删除角色
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('setting:role:del')")
    public Response<Object> del(@PathVariable Integer id){
        return roleService.delete(id);
    }


    @PostMapping("permission")
    public Response<Object> updatePermissions(@RequestBody @Validated RolePermissionReq rolePermissionReq){
        return roleService.updatePermissions(rolePermissionReq);
    }


}
