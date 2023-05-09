package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.permission.PermissionPageReq;
import com.jenschen.request.permission.PermissionReq;
import com.jenschen.service.PermissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制器
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService){
        this.permissionService = permissionService;
    }

    @PostMapping(value = "/page")
    @ResponseBody
    public Response<Object> page(@RequestBody PermissionPageReq permissionPageReq){
        return permissionService.page(permissionPageReq);
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Response<Object> all(){
        return permissionService.all();
    }

    @GetMapping(value = "/{roleId}")
    @ResponseBody
    public Response<Object> getPermissionsByRoleId(@PathVariable Integer roleId){
        return permissionService.getPermissionsByRoleId(roleId);
    }

    @PutMapping
    public Response<Object> add(@RequestBody @Validated PermissionReq permissionReq){
        return permissionService.add(permissionReq);
    }

    @PostMapping
    public Response<Object> edit(@RequestBody @Validated PermissionReq permissionReq){
        return permissionService.edit(permissionReq);
    }

    /**
     * 根据 id 删除权限
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public Response<Object> del(@PathVariable Integer id){
        return permissionService.delete(id);
    }


}
