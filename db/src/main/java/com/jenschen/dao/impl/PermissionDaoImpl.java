package com.jenschen.dao.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.constant.CommonConstant;
import com.jenschen.dao.AbstractDao;
import com.jenschen.dao.PermissionDao;
import com.jenschen.entity.PermissionEntity;
import com.jenschen.mapper.PermissionMapper;
import com.jenschen.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionDaoImpl extends AbstractDao<PermissionEntity> implements PermissionDao {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<PermissionEntity> all() {
        QueryWrapper<PermissionEntity> queryWrapper = this.getDefaultQuery();
        return permissionMapper.selectList(queryWrapper);
    }

    @Override
    public List<PermissionEntity> getPermissionByRoleId(Integer roleId) {
        if(roleId == 1){
            return all();
        }

        return rolePermissionMapper.getPermissionByRoleId(roleId);
    }

    @Override
    public List<PermissionEntity> getPermissionByRoleIds(List<String> roleIds) {
        for(var id : roleIds){
            if(Integer.parseInt(id) == CommonConstant.DEFAULT_ROLE_ADMIN_ID){
                return rolePermissionMapper.getAdminPermission();
            }
        }
        return rolePermissionMapper.getPermissionByRoleIds(roleIds);
    }

    @Override
    public BaseMapper<PermissionEntity> getMapper() {
        return permissionMapper;
    }
}
