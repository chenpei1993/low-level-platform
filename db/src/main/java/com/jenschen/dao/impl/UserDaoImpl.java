package com.jenschen.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenschen.dao.AbstractDao;
import com.jenschen.dao.TagDao;
import com.jenschen.dao.UserDao;
import com.jenschen.entity.TagEntity;
import com.jenschen.entity.UserEntity;
import com.jenschen.mapper.TagMapper;
import com.jenschen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {

    @Autowired
    private UserMapper userMapper;


    @Override
    public BaseMapper<UserEntity> getMapper() {
        return userMapper;
    }
}
