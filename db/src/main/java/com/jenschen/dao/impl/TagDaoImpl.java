package com.jenschen.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.dao.AbstractDao;
import com.jenschen.dao.RoleDao;
import com.jenschen.dao.TagDao;
import com.jenschen.entity.RoleEntity;
import com.jenschen.entity.TagEntity;
import com.jenschen.mapper.RoleMapper;
import com.jenschen.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagDaoImpl extends AbstractDao<TagEntity> implements TagDao {

    @Autowired
    private TagMapper tagMapper;


    @Override
    public BaseMapper<TagEntity> getMapper() {
        return tagMapper;
    }
}
