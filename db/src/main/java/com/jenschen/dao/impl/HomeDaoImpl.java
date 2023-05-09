package com.jenschen.dao.impl;

import com.jenschen.dao.HomeDao;
import com.jenschen.entity.HomeEntity;
import com.jenschen.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeDaoImpl implements HomeDao {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public HomeEntity getBasicHomeInfo(String username) {
        return homeMapper.getBasicHomeInfo(username);
    }
}
