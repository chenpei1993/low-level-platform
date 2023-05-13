package com.jenschen.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.dao.AbstractDao;
import com.jenschen.dao.InfoDao;
import com.jenschen.dao.RoleDao;
import com.jenschen.entity.InfoEntity;
import com.jenschen.entity.RoleEntity;
import com.jenschen.mapper.InfoMapper;
import com.jenschen.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDaoImpl extends AbstractDao<RoleEntity> implements RoleDao {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public BaseMapper<RoleEntity> getMapper() {
        return roleMapper;
    }
}
