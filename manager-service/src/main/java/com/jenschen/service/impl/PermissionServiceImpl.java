package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.constant.CommonConstant;
import com.jenschen.dao.PermissionDao;
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

    private final PermissionDao permissionDao;

    public PermissionServiceImpl(PermissionDao permissionDao){
        this.permissionDao = permissionDao;
    }

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<PermissionEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<PermissionEntity> permissionEntityList = permissionDao.selectList(queryWrapper);
        List<PermissionResp> resp = BeanUtil.copyToList(permissionEntityList, PermissionResp.class);
        int count = permissionDao.selectCount(this.getDefaultQuery());
        return ResultUtil.success(PageResp.build(count, resp));
    }

    @Override
    public Response<Object> all() {
        List<PermissionEntity> permissionEntityList = permissionDao.all();
        return ResultUtil.success(permissionEntityList);
    }

    @Override
    public Response<Object> getPermissionsByRoleId(Integer roleId) {
        if(roleId == 1){
            return ResultUtil.success(permissionDao.all());
        }

        List<PermissionEntity> permissionEntityList = permissionDao.getPermissionByRoleId(roleId);
        return ResultUtil.success(permissionEntityList);
    }

    @Override
    public List<PermissionEntity> getPermissionByRoleIds(List<String> roleIds) {
        for(var id : roleIds){
            if(Integer.parseInt(id) == CommonConstant.DEFAULT_ROLE_ADMIN_ID){
                return permissionDao.all();
            }
        }
        return permissionDao.getPermissionByRoleIds(roleIds);
    }

    @Override
    public Response<Object> add(PermissionReq permissionReq) {
        //TODO 是否需要验证标签名是否唯一
        PermissionEntity permission = BeanUtil.copyProperties(permissionReq, PermissionEntity.class);
        permission.created(LocalDateTime.now(),1);
        permissionDao.insert(permission);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> edit(PermissionReq permissionReq) {
        PermissionEntity permission = BeanUtil.copyProperties(permissionReq, PermissionEntity.class);
        permission.updated(LocalDateTime.now(), 1);
        permissionDao.updateById(permission);
        return ResultUtil.success();
    }


    @Override
    public Response<Object> delete(Integer id) {
        PermissionEntity entity = PermissionEntity.builder().build();
        entity.setId(id);
        entity.deleted(LocalDateTime.now(), 1);
        permissionDao.updateById(entity);

        //TODO 删除角色下的标签
        return ResultUtil.success();
    }

}
