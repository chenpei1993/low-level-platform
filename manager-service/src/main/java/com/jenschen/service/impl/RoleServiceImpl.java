package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.constant.CommonConstant;
import com.jenschen.entity.PermissionEntity;
import com.jenschen.entity.RoleEntity;
import com.jenschen.entity.RolePermissionEntity;
import com.jenschen.enumeration.ErrorEnum;
import com.jenschen.mapper.RoleMapper;
import com.jenschen.mapper.RolePermissionMapper;
import com.jenschen.mapper.RoleUserMapper;
import com.jenschen.request.Page;
import com.jenschen.request.role.RolePermissionReq;
import com.jenschen.request.role.RoleReq;
import com.jenschen.response.PageResp;
import com.jenschen.response.RoleResp;
import com.jenschen.service.AbstractService;
import com.jenschen.service.RoleService;
import com.jenschen.util.ResultUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl extends AbstractService<RoleEntity> implements RoleService {
    private final RoleMapper roleMapper;

    private final RoleUserMapper roleUserMapper;

    private final RolePermissionMapper rolePermissionMapper;

    public RoleServiceImpl(RoleMapper roleMapper, RoleUserMapper roleUserMapper, RolePermissionMapper rolePermissionMapper){
        this.roleMapper = roleMapper;
        this.roleUserMapper = roleUserMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<RoleEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<RoleEntity> roleEntityList = roleMapper.selectList(queryWrapper);
        List<RoleResp> resp = BeanUtil.copyToList(roleEntityList, RoleResp.class);
        int count = roleMapper.selectCount(this.getDefaultQuery());
        return ResultUtil.success(PageResp.build(count, resp));
    }

    @Override
    public Response<Object> all() {
        QueryWrapper<RoleEntity> queryWrapper = this.getDefaultQuery();
        List<RoleEntity> roleEntityList = roleMapper.selectList(queryWrapper);
        return ResultUtil.success(roleEntityList);
    }

    @Override
    public Response<Object> add(RoleReq roleReq) {
        //TODO 是否需要验证标签名是否唯一
        RoleEntity role = BeanUtil.copyProperties(roleReq, RoleEntity.class);
        role.created(LocalDateTime.now(),1);
        roleMapper.insert(role);
        return ResultUtil.success();
    }

    @Override
    public Response<Object> edit(RoleReq roleReq) {
        //默认角色admin禁止编辑
        if(roleReq.getId() == CommonConstant.DEFAULT_ROLE_ADMIN_ID){
            return ResultUtil.error(ErrorEnum.ACCESS_DENIED);
        }

        RoleEntity role = BeanUtil.copyProperties(roleReq, RoleEntity.class);
        role.updated(LocalDateTime.now(), 1);
        roleMapper.updateById(role);
        return ResultUtil.success();
    }


    @Override
    public Response<Object> delete(Integer id) {
        //默认角色admin禁止删除
        if(id == CommonConstant.DEFAULT_ROLE_ADMIN_ID){
            return ResultUtil.error(ErrorEnum.ACCESS_DENIED);
        }

        RoleEntity entity = RoleEntity.builder().build();
        entity.setId(id);
        entity.deleted(LocalDateTime.now(), 1);
        roleMapper.updateById(entity);

        //同时删除角色用户关联表中数据
        roleUserMapper.deleteByRoleId(id, 1);

        return ResultUtil.success();
    }

    @Override
    public Response<Object> updatePermissions(RolePermissionReq rolePermissionReq) {
        //默认角色admin禁止删除
        if(rolePermissionReq.getRoleId() == CommonConstant.DEFAULT_ROLE_ADMIN_ID){
            return ResultUtil.error(ErrorEnum.ACCESS_DENIED);
        }

        //删除角色权限表中的数据
        rolePermissionMapper.deletePermissionByRoleId(rolePermissionReq.getRoleId(), 1);

        //重新插入
        for(Integer permissionIds : rolePermissionReq.getPermissionIds()){
            RolePermissionEntity entity = RolePermissionEntity
                                                .builder()
                                                .role_id(rolePermissionReq.getRoleId())
                                                .permission_id(permissionIds)
                                                .build();
            entity.created(LocalDateTime.now(), 1);
            rolePermissionMapper.insert(entity);
        }

        return ResultUtil.success();
    }

}
