package com.jenschen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jenschen.base.Response;
import com.jenschen.entity.RoleEntity;
import com.jenschen.mapper.RoleMapper;
import com.jenschen.request.Page;
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

    public RoleServiceImpl(RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }

    @Override
    public Response<Object> page(Page page) {
        QueryWrapper<RoleEntity> queryWrapper = this.getPageQueryWrapper(page);
        List<RoleEntity> permissionEntityList = roleMapper.selectList(queryWrapper);
        List<RoleResp> resp = BeanUtil.copyToList(permissionEntityList, RoleResp.class);
        int count = roleMapper.selectCount(queryWrapper);
        return ResultUtil.success(PageResp.build(count, resp));
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
        RoleEntity role = BeanUtil.copyProperties(roleReq, RoleEntity.class);
        role.updated(LocalDateTime.now(), 1);
        roleMapper.updateById(role);
        return ResultUtil.success();
    }


    @Override
    public Response<Object> delete(Integer id) {
        RoleEntity entity = RoleEntity.builder().build();
        entity.setId(id);
        entity.deleted(LocalDateTime.now(), 1);
        roleMapper.updateById(entity);
        //TODO 删除用户的标签
        return ResultUtil.success();
    }

}
