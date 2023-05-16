package com.jenschen.controller;

import com.jenschen.base.Response;
import com.jenschen.request.permission.PermissionPageReq;
import com.jenschen.request.permission.PermissionReq;
import com.jenschen.service.PermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('setting:permission:query')")
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
    @PreAuthorize("hasAuthority('setting:permission:add')")
    public Response<Object> add(@RequestBody @Validated PermissionReq permissionReq){
        return permissionService.add(permissionReq);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('setting:permission:edit')")
    public Response<Object> edit(@RequestBody @Validated PermissionReq permissionReq){
        return permissionService.edit(permissionReq);
    }

    /**
     * 根据 id 删除权限
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('setting:permission:del')")
    public Response<Object> del(@PathVariable Integer id){
        return permissionService.delete(id);
    }


}
