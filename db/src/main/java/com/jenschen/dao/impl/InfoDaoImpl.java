package com.jenschen.dao.impl;

import com.jenschen.dao.AbstractDao;
import com.jenschen.dao.InfoDao;
import com.jenschen.dao.QuestionDao;
import com.jenschen.entity.InfoEntity;
import com.jenschen.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoDaoImpl extends AbstractDao<InfoDao> implements InfoDao {

    @Autowired
    private InfoMapper infoMapper;


    @Override
    public InfoEntity getByCode(String code) {
        return infoMapper.getByCode(code);
    }

    @Override
    public List<InfoEntity> getLastNInfo(Integer num) {
        return infoMapper.getLastNInfo(num);
    }
}
