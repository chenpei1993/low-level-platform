package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.entity.PermissionEntity;
import com.jenschen.mapper.PermissionMapper;
import com.jenschen.mapper.RolePermissionMapper;
import com.jenschen.request.Page;
import com.jenschen.request.permission.PermissionReq;
import com.jenschen.response.PageResp;
import com.jenschen.response.PermissionResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.PermissionService;
import com.jenschen.util.ResultUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PermissionServiceImpl extends AbstractService<PermissionEntity> implements PermissionService {
    private final PermissionMapper permissionMapper;
    private final RolePermissionMapper rolePermissionMapper;

    public PermissionServiceImpl(PermissionMapper permissionMapper, RolePermissionMapper rolePermissionMapper){
        this.permissionMapper = permissionMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<PermissionEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<PermissionEntity> permissionEntityList = permissionMapper.selectList(queryWrapper);
        List<PermissionResp> resp = BeanUtil.copyToList(permissionEntityList, PermissionResp.class);
        int count = permissionMapper.selectCount(this.getDefaultQuery());
        return ResultUtil.success(PageResp.build(count, resp));
    }

    @Override
    public Response<Object> all() {
        QueryWrapper<PermissionEntity> queryWrapper = this.getDefaultQuery();
        List<PermissionEntity> permissionEntityList = permissionMapper.selectList(queryWrapper);
        return ResultUtil.success(permissionEntityList);
    }

    @Override
    public Response<Object> getPermissionsByRoleId(Integer roleId) {
        if(roleId == 1){
            return all();
        }

        List<PermissionEntity> permissionEntityList = rolePermissionMapper.getPermissionByRoleId(roleId);
        return ResultUtil.success(permissionEntityList);
    }

    @Override
    public List<PermissionEntity> getPermissionByRoleIds(List<String> roleIds) {
        return rolePermissionMapper.getPermissionByRoleIds(roleIds);
    }

    @Override
    public Response<Object> add(PermissionReq permissionReq) {
        //TODO 是否需要验证标签名是否唯一
        PermissionEntity permission = BeanUtil.copyProperties(permissionReq, PermissionEntity.class);
        permission.created(LocalDateTime.now(),1);
        permissionMapper.insert(permission);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> edit(PermissionReq permissionReq) {
        PermissionEntity permission = BeanUtil.copyProperties(permissionReq, PermissionEntity.class);
        permission.updated(LocalDateTime.now(), 1);
        permissionMapper.updateById(permission);
        return ResultUtil.success();
    }


    @Override
    public Response<Object> delete(Integer id) {
        PermissionEntity entity = PermissionEntity.builder().build();
        entity.setId(id);
        entity.deleted(LocalDateTime.now(), 1);
        permissionMapper.updateById(entity);

        //TODO 删除角色下的标签
        return ResultUtil.success();
    }

}
